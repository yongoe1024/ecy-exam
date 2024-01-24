<template>
  <div>
    <div>
      <el-input v-model="queryParam.examName"
                style="width:300px"
                prefix-icon="el-icon-search"
                placeholder="请输入考试名称"></el-input>
      <el-button type="primary"
                 style="margin:5px"
                 plain
                 icon="el-icon-search"
                 @click="getList">搜索</el-button>
    </div>

    <el-row :gutter="5"
            style="margin:20px 0 20px 0">
      <el-empty v-if="dataList.length==0"
                description="暂无考试"></el-empty>
      <el-col :sm="24"
              :md="8"
              :lg="6"
              v-for="item in dataList"
              :key="item.id">
        <el-card class="card">
          <div slot="header">
            <span>{{item.examInfo.examName}}</span>
            <el-button v-if="item.examInfo.status == 3 && (item.status == 1||item.status == 2)"
                       @click="kaoshi(item.examInfo.id)"
                       style="float: right; padding: 3px 0"
                       type="text">开始考试</el-button>
            <el-button v-if="(item.status == 5||item.status == 4)"
                       @click="examInfo(item.id)"
                       style="float: right; padding: 3px 0"
                       type="text">查看详情</el-button>
          </div>
          <el-descriptions :column="1">
            <el-descriptions-item label="试卷状态">
              <e-dict name="试卷状态"
                      :value="item.status"
                      size="small"
                      type="tag"></e-dict>
              <el-tag v-if="item.status == 4 || item.status == 5"
                      size="small"
                      style="margin-left:10px"> 总分：{{item.score}} </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="开始时间">{{item.examInfo.startTime}}</el-descriptions-item>
            <el-descriptions-item label="结束时间">{{item.examInfo.endTime}}</el-descriptions-item>
            <el-descriptions-item label="考试时间">{{item.examInfo.duration}}分钟</el-descriptions-item>
            <el-descriptions-item label="考试状态">
              <e-dict name="考试状态"
                      v-model="item.examInfo.status"
                      size="small"
                      type="tag"></e-dict>
            </el-descriptions-item>
            <el-descriptions-item label="描述">{{item.examInfo.examDesc}}</el-descriptions-item>

          </el-descriptions>

        </el-card>
      </el-col>
    </el-row>

    <!-- 分页 -->
    <el-pagination background
                   style="display:flex;justify-content:center;"
                   @size-change="handleSizeChange"
                   @current-change="handleCurrentChange"
                   :page-size="size"
                   layout="total, sizes, prev, pager, next, jumper"
                   :total="total"></el-pagination>
  </div>
</template>

<script>
export default {
  data () {
    return {
      total: 0,
      current: 1,
      size: 10,
      dataList: [],
      queryParam: {
        examName: null,
      },
    }
  },
  mounted () {
    this.getList()
  },
  methods: {
    examInfo (paperId) {
      this.$router.push({ path: '/paper/ExamDetails', query: { paperId: paperId } })
    },
    kaoshi (examId) {
      const href = this.$router.resolve({
        path: `/examination/index`,
        query: { examId: examId },
      }).href
      //打开新的页面 到 href 这个页面
      window.open(href, "_blank")
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
    // 初始化数据
    getList () {
      this.axios.post(`/exam/userpaper/page?current=${this.current}&size=${this.size}`, this.queryParam).then(data => {
        this.dataList = data.list
        this.total = data.total - 0
      }).catch(e => { })
    },
  },
}

</script>
<style scoped>
.card {
  min-height: 200px;
}
</style>