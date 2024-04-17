<template>
  <div>
    <!-- 搜索 -->
    <div v-auth="'查'"
         class="head">
      <el-select v-model="queryParam.questionBankId"
                 size="small"
                 filterable
                 placeholder="请选择题库">
        <el-option v-for="item in bankList"
                   :key="item.id"
                   :label="item.name"
                   :value="item.id">
        </el-option>
      </el-select>
      <el-input v-model="queryParam.content"
                size="small"
                prefix-icon="el-icon-search"
                placeholder="请输入题目"></el-input>
      <e-dict name="题目类型"
              v-model="queryParam.type"
              size="small"
              placeholder="请输入类型"></e-dict>
      <e-dict name="题目难度"
              v-model="queryParam.level"
              size="small"
              placeholder="请输入题目难度"></e-dict>
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
      <el-table-column align="center"
                       type="index"></el-table-column>
      <el-table-column prop="content"
                       label="题目"
                       align="center">
        <template slot-scope="scope">
          <div v-html="scope.row.content"
               class="content"></div>
        </template>
      </el-table-column>
      <el-table-column prop="type"
                       label="类型"
                       width="150"
                       align="center">
        <template slot-scope="scope">
          <e-dict name="题目类型"
                  v-model="scope.row.type"
                  type="tag"></e-dict>
        </template>
      </el-table-column>
      <el-table-column prop="level"
                       label="题目难度"
                       width="150"
                       align="center">
        <template slot-scope="scope">
          <e-dict name="题目难度"
                  v-model="scope.row.level"
                  type="tag"></e-dict>
        </template>
      </el-table-column>
      <el-table-column label="操作"
                       align="center"
                       width="200"
                       fixed="right">
        <template slot-scope="scope">
          <el-button v-auth="'查'"
                     v-if="scope.row.type == 1 || scope.row.type == 2 || scope.row.type == 3 "
                     type="text"
                     size="mini"
                     @click="handleOption(scope.row)">选项</el-button>
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
               :close-on-click-modal="false"
               width="600px">
      <el-form ref="form"
               :model="form"
               label-width="auto"
               style="margin:20px"
               :rules="rules">
        <el-form-item label="题库"
                      prop="questionBankId">
          <el-select v-model="form.questionBankId"
                     filterable
                     placeholder="请选择题库">
            <el-option v-for="item in bankList"
                       :key="item.id"
                       :label="item.name"
                       :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="类型"
                      prop="type">
          <e-dict name="题目类型"
                  type="radio"
                  v-model="form.type"
                  placeholder="请输入类型"></e-dict>
        </el-form-item>
        <el-form-item label="题目难度"
                      prop="level">
          <e-dict name="题目难度"
                  v-model="form.level"
                  type="radio"
                  placeholder="请输入题目难度"></e-dict>
        </el-form-item>
        <el-form-item label="题目"
                      prop="content">
          <e-editor v-model="form.content"></e-editor>
        </el-form-item>
        <el-form-item label="参考答案"
                      v-if="form.type=='4'"
                      prop="answer">
          <e-editor v-model="form.answer"></e-editor>
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
  components: {},
  props: {},
  data () {
    return {
      // 多选框数据
      multipleSelection: [],
      total: 0,
      current: 1,
      size: 10,
      dialogVisible: false,
      dialogTitle: '',

      bankList: [],
      dataList: [],
      form: {
        id: null,
        questionBankId: null,
        content: null,
        type: null,
        level: '正常',
        answer: null,
      },
      queryParam: {
        questionBankId: null,
        content: null,
        type: null,
        level: null,
      },
      rules: {
        questionBankId: [{ required: true, message: '请输入题库', trigger: 'change' }],
        content: [{ required: true, message: '请输入题目', trigger: 'change' }],
        type: [{ required: true, message: '请输入类型', trigger: 'change' }],
        level: [{ required: true, message: '请输入题目难度', trigger: 'change' }],
        answer: [{ required: true, message: '请输入参考答案', trigger: 'change' }],
      },
    }
  },
  mounted () {
    this.getList()
    this.getbankList()
  },
  methods: {
    //跳转到 选项
    handleOption (row) {
      this.$router.push('/question/QuestionOption?questionId=' + row.id)
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
        if (valid) {
          if (this.form.id) {
            this.axios.post('/exam/question/update', this.form).then(() => {
              this.getList()
              this.dialogVisible = false
            }).catch(e => { })
          } else {
            this.axios.post('/exam/question/add', this.form).then(() => {
              this.getList()
              this.dialogVisible = false
            }).catch(e => { })
          }
        }
      })
    },
    handleDelete (row) {
      this.$confirm('此操作将永久删除这条数据, 是否继续?', '提示', { type: 'warning' }).then(() => {
        this.axios.post('/exam/question/delete/' + row.id).then(() => this.getList()).catch(e => { })
      }).catch(e => { })
    },
    handleDeleteMany () {
      this.$confirm('此操作将永久删除 [' + this.multipleSelection.length + '] 条数据, 是否继续?', '提示', { type: 'warning' }).then(() => {
        this.axios.post('/exam/question/delete/' + this.multipleSelection).then(() => this.getList()).catch(e => { })
      }).catch(e => { })
    },
    // 初始化数据
    getbankList () {
      this.axios.post(`/exam/question/banklist`).then(data => {
        this.bankList = data
      }).catch(e => { })
    },
    getList () {
      this.axios.post(`/exam/question/page?current=${this.current}&size=${this.size}`, this.queryParam).then(data => {
        this.form.questionBankId = this.queryParam.questionBankId
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
.content {
  max-height: 100px;
}
.content >>> img {
  display: none;
}
</style>