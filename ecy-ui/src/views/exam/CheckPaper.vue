<template>
  <div>
    <el-page-header @back="$router.back()"
                    style="margin-bottom:30px"></el-page-header>
    <div class="head">
      <e-dict name="试卷状态"
              placeholder="试卷状态"
              v-model="queryParam.status"></e-dict>
    </div>
    <div class="button">
      <el-button type="primary"
                 size="mini"
                 plain
                 icon="el-icon-search"
                 @click="getList">搜索</el-button>
      <el-button size="mini"
                 plain
                 icon="el-icon-refresh"
                 @click="resetQuery">重置</el-button>
      <el-button icon="el-icon-download"
                 size="mini"
                 type="warning"
                 plain
                 @click="handleExport">导出数据</el-button>
      <el-button icon="el-icon-edit"
                 size="mini"
                 type="warning"
                 plain
                 @click="autoScore">自动判分</el-button>
    </div>

    <!-- 表格 -->
    <el-table :data="dataList"
              style="width: 100%"
              :header-cell-style="{background:'#eef1f6'}">
      <el-table-column align="center"
                       type="index"></el-table-column>
      <el-table-column prop="name"
                       label="用户"
                       align="center"></el-table-column>
      <el-table-column prop="status"
                       label="考试状态"
                       align="center">
        <template slot-scope="scope">
          <e-dict name="试卷状态"
                  :value="scope.row.status"
                  type="tag"></e-dict>
        </template>
      </el-table-column>
      <el-table-column prop="enterTime"
                       label="开始时间"
                       align="center"></el-table-column>
      <el-table-column prop="finishTime"
                       label="交卷时间"
                       align="center"></el-table-column>
      <el-table-column prop="score"
                       label="得分"
                       align="center"></el-table-column>
      <el-table-column label="操作"
                       align="center"
                       width="140"
                       fixed="right">
        <template slot-scope="scope">
          <el-button v-if="scope.row.status!=1 && scope.row.status!=2"
                     type="text"
                     size="mini"
                     icon="el-icon-edit"
                     @click="handleShowUpdateEdit(scope.row)">批改</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination background
                   style="display:flex;justify-content:center;"
                   @size-change="handleSizeChange"
                   @current-change="handleCurrentChange"
                   :page-size="size"
                   layout="total, sizes, prev, pager, next, jumper"
                   :total="total"></el-pagination>

    <el-dialog title="提示"
               :visible.sync="dialogVisible"
               width="50%"
               :close-on-click-modal="false">
      <span>判卷中请稍后</span>
    </el-dialog>

  </div>
</template>

<script>
export default {
  components: {},
  data () {
    return {
      total: 0,
      current: 1,
      size: 10,
      dialogVisible: false,
      dataList: [],
      queryParam: {
        examId: null,
        status: null,
      },
    }
  },
  mounted () {
    this.getList()
  },
  methods: {
    handleExport () {
      this.queryParam.examId = this.$route.query.examId
      this.download('/exam/userpaper/export', this.queryParam)
    },
    //跳转到 题目批改
    handleShowUpdateEdit (row) {
      this.$router.push({ path: '/exam/CheckQuestion', query: { paperId: row.id } })
    },
    resetQuery () {
      this.queryParam = this.$options.data().queryParam
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
    // 自动判分
    autoScore () {
      this.$confirm('ai判卷，需要较长时间, 是否继续?', '提示', { type: 'warning' }).then(() => {
        this.dialogVisible = true
        this.axios.post(`/exam/userpaper/autoScore?examId=${this.$route.query.examId}`).then(data => {
          this.getList()
          this.dialogVisible = false
        }).catch(e => { })
      }).catch(e => { })

    },
    // 初始化数据
    getList () {
      this.queryParam.examId = this.$route.query.examId
      this.axios.post(`/exam/userpaper/checkPaper?current=${this.current}&size=${this.size}`, this.queryParam).then(data => {
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