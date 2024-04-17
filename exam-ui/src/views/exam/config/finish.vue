<template>
  <div>
    <el-row>
      <el-col v-if="!finish"
              :offset="2"
              :sm="12"
              :lg="6">
        <el-result icon="warning"
                   title="提示"
                   subTitle="提交后无法修改">
          <template slot="extra">
            <el-button @click="submit"
                       type="primary"
                       size="medium">提交</el-button>
          </template>
        </el-result>
      </el-col>
      <el-col v-if="finish"
              :offset="2"
              :sm="12"
              :lg="6">
        <el-result icon="success"
                   title="发布成功"
                   subTitle="试卷已经下发至考生">
          <template slot="extra">
            <el-button type="primary"
                       @click="$router.push('/exam/ExamInfo')"
                       size="medium">返回</el-button>
          </template>
        </el-result>
      </el-col>
    </el-row>
  </div>
</template>


<script>
export default {
  props: ['next'],
  computed: {},
  watch: {},
  data () {
    return {
      finish: false,
    }
  },
  mounted () { },
  methods: {
    submit () {
      let openType = JSON.parse(window.localStorage.getItem('form'))
      let bank = JSON.parse(window.localStorage.getItem('form2'))
      console.log(openType)
      console.log(bank)
      let data = {
        ...openType,
        ...bank,
        examId: this.$route.query.examId,
      }
      this.axios.post(`/exam/config/makepaper`, data).then(res => {
        this.finish = true
        this.next(1)
      }).catch(err => { })
    }
  },
}

</script>
<style scoped>
</style>