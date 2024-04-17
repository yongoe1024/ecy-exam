<template>
  <div>
    <!-- 搜索 -->
    <div v-auth="'查'"
         class="head">
      <el-input v-model="queryParam.examName"
                size="small"
                prefix-icon="el-icon-search"
                placeholder="请输入考试名称"></el-input>
      <el-input v-model="queryParam.examDesc"
                size="small"
                prefix-icon="el-icon-search"
                placeholder="请输入考试描述"></el-input>
      <e-dict name="考试状态"
              size="small"
              v-model="queryParam.status"
              placeholder="考试状态"> </e-dict>
    </div>
    <!-- 按钮 -->
    <div class="button">
      <el-button type="primary"
                 v-auth="'查'"
                 size="mini"
                 plain
                 icon="el-icon-search"
                 @click="getList">搜索</el-button>
      <el-button v-auth="'查'"
                 size="mini"
                 plain
                 icon="el-icon-refresh"
                 @click="resetQuery">重置</el-button>
      <el-button type="success"
                 v-auth="'增'"
                 size="mini"
                 plain
                 @click="handleShowAddEdit"
                 icon="el-icon-plus">添加</el-button>
    </div>

    <!-- 表格 -->
    <el-table :data="dataList"
              style="width: 100%"
              :header-cell-style="{background:'#eef1f6'}"
              @selection-change="handleSelectionChange">
      <el-table-column align="center"
                       type="selection"
                       width="55"></el-table-column>
      <!-- <el-table-column align="center"
                       type="index"></el-table-column> -->
      <el-table-column prop="examName"
                       label="考试名称"
                       align="center"></el-table-column>
      <el-table-column prop="duration"
                       label="考试时长(分钟)"
                       align="center"></el-table-column>
      <el-table-column prop="startTime"
                       label="开始时间"
                       align="center"></el-table-column>
      <el-table-column prop="endTime"
                       label="结束时间"
                       align="center"></el-table-column>
      <el-table-column prop="totalScore"
                       label="总分"
                       align="center"></el-table-column>
      <el-table-column prop="passScore"
                       label="及格分"
                       align="center"></el-table-column>
      <el-table-column prop="status"
                       label="状态"
                       align="center">
        <template slot-scope="scope">
          <e-dict name="考试状态"
                  v-model="scope.row.status"
                  type="tag"></e-dict>
        </template>
      </el-table-column>
      <el-table-column prop="createTime"
                       label="创建时间"
                       align="center"></el-table-column>
      <el-table-column label="操作"
                       align="center"
                       width="200"
                       fixed="right">
        <template slot-scope="scope">
          <el-button v-if="scope.row.status == 4 ||  scope.row.status == 3"
                     type="text"
                     size="mini"
                     @click="handleCheckPaper(scope.row)">阅卷</el-button>
          <el-button v-if="scope.row.status == 1"
                     v-auth="'改'"
                     type="text"
                     size="mini"
                     @click="handleConfigExam(scope.row)">组卷</el-button>
          <el-button v-auth="'改'"
                     type="text"
                     size="mini"
                     icon="el-icon-edit"
                     @click="handleShowUpdateEdit(scope.row)">编辑</el-button>
          <el-button v-auth="'删'"
                     type="text"
                     size="mini"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 批量删除 -->
    <el-button style="margin-top: 8px"
               v-auth="'删'"
               type="danger"
               size="small"
               :disabled="multipleSelection.length == 0"
               @click="handleDeleteMany">批量删除</el-button>
    <!-- 分页 -->
    <el-pagination background
                   style="display:flex;justify-content:center;"
                   @size-change="handleSizeChange"
                   @current-change="handleCurrentChange"
                   :page-size="size"
                   layout="total, sizes, prev, pager, next, jumper"
                   :total="total"></el-pagination>

    <el-dialog :visible.sync="dialogVisible"
               :title="dialogTitle"
               @close="reset"
               :destroy-on-close="true"
               :close-on-click-modal="false"
               width="600px">
      <el-form ref="form"
               :model="form"
               label-width="auto"
               style="margin:20px"
               :rules="rules">
        <el-form-item label="考试名称"
                      prop="examName">
          <el-input v-model="form.examName"
                    placeholder="请输入考试名称" />
        </el-form-item>
        <el-form-item label="考试描述"
                      prop="examDesc">
          <el-input v-model="form.examDesc"
                    placeholder="请输入考试描述" />
        </el-form-item>
        <el-form-item label="开始时间"
                      prop="startTime">
          <el-date-picker v-model="form.startTime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          type="datetime"
                          placeholder="开始时间"></el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间"
                      prop="endTime">
          <el-date-picker v-model="form.endTime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          type="datetime"
                          placeholder="结束时间"></el-date-picker>
        </el-form-item>
        <el-form-item label="考试时长(分钟)"
                      prop="duration">
          <el-input-number :min="10"
                           :max="duration"
                           v-model="form.duration" />

        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button type="primary"
                   @click="handleAddOrUpdate">确 定</el-button>
        <el-button @click="dialogVisible = false">取 消</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  props: {},
  computed: {
    duration () {
      return parseInt((this.end.getTime() - this.start.getTime()) / 1000 / 60)
    },
    start () {
      return new Date(this.form.startTime)
    },
    end () {
      return new Date(this.form.endTime)
    },
  },
  data () {
    return {
      // 多选框数据
      multipleSelection: [],
      total: 0,
      current: 1,
      size: 10,
      dialogVisible: false,
      dialogTitle: '',

      dataList: [],
      form: {
        id: null,
        examName: null,
        examDesc: null,
        duration: null,
        startTime: null,
        endTime: null,
        status: '1',
      },
      queryParam: {
        id: null,
        examName: null,
        examDesc: null,
        status: null,
      },
      rules: {
        examName: [{ required: true, message: '请输入考试名称', trigger: 'change' }],
        examDesc: [{ required: true, message: '请输入考试描述', trigger: 'change' }],
        duration: [{ required: true, message: '请输入考试时长(分钟)', trigger: 'change' }],
        startTime: [{ required: true, message: '请输入开始时间', trigger: 'change' }],
        endTime: [{ required: true, message: '请输入结束时间', trigger: 'change' }],
      },
    }
  },
  mounted () {
    this.getList()
  },
  methods: {
    //阅卷
    handleCheckPaper (row) {
      this.$router.push({ path: '/exam/CheckPaper', query: { examId: row.id } })
    },
    //组卷
    handleConfigExam (row) {
      this.$router.push({ path: '/exam/config/Config', query: { examId: row.id } })
    },
    reset () {
      this.form = this.$options.data().form
    },
    resetQuery () {
      this.queryParam = this.$options.data().queryParam
    },
    // 多选框回调
    handleSelectionChange (val) {
      this.multipleSelection = val.map(item => item.id)
    },
    // 改变页码
    handleSizeChange (val) {
      this.size = val
      this.getList()
    },
    // 点击页数
    handleCurrentChange (val) {
      this.current = val
      this.getList()
    },
    handleShowAddEdit () {
      this.dialogTitle = '添加'
      this.dialogVisible = true
    },
    handleShowUpdateEdit (row) {
      this.dialogTitle = '修改'
      Object.assign(this.form, row)
      this.dialogVisible = true
    },
    handleAddOrUpdate () {
      this.$refs.form.validate((valid) => {
        let timeDifference = parseInt(this.end.getTime()) - parseInt(this.start.getTime())
        if (timeDifference < 0) {
          this.$message.error('考试结束时间不能小于考试开始时间')
          return
        }
        if (this.form.duration < 10) {
          this.$message.error('考试时长不能小于10分钟')
          return
        }
        if (valid) {
          if (this.form.id) {
            this.axios.post('/exam/examinfo/update', this.form).then(() => {
              this.getList()
              this.dialogVisible = false
            }).catch(e => { })
          } else {
            this.axios.post('/exam/examinfo/add', this.form).then(() => {
              this.getList()
              this.dialogVisible = false
            }).catch(e => { })
          }
        }
      })
    },
    handleDelete (row) {
      this.$confirm('此操作将永久删除这条数据, 是否继续?', '提示', { type: 'warning' }).then(() => {
        this.axios.post('/exam/examinfo/delete/' + row.id).then(() => this.getList()).catch(e => { })
      }).catch(e => { })
    },
    handleDeleteMany () {
      this.$confirm('此操作将永久删除 [' + this.multipleSelection.length + '] 条数据, 是否继续?', '提示', { type: 'warning' }).then(() => {
        this.axios.post('/exam/examinfo/delete/' + this.multipleSelection).then(() => this.getList()).catch(e => { })
      }).catch(e => { })
    },
    // 初始化数据
    getList () {
      this.axios.post(`/exam/examinfo/page?current=${this.current}&size=${this.size}`, this.queryParam).then(data => {
        this.dataList = data.list
        this.total = data.total - 0
      }).catch(e => { })
    },
  },
}
</script>
<style scoped>
.head {
  display: flex;
  flex-wrap: wrap;
}
.head .el-input {
  width: 200px !important;
}
.head * {
  margin: 0 8px 8px 0;
}
.button {
  margin: 0 0 15px 0;
  display: flex;
}
.button * {
  margin: 0 8px 0 0;
}
</style>