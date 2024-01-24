<template>
  <div class="box">
    <el-row>
      <el-col :offset="2"
              :span="20">
        <div v-for="(item, index) in questionList"
             :key="index">
          <el-card style="margin:10px">
            <div slot="header">
              <span>第{{index+1}}题</span>
              <el-tag style="float: right;"> {{item.totalScore}}分</el-tag>
              <e-dict name="题目类型"
                      :value="item.type"
                      type="tag"
                      style="float: right;margin-right:30px"></e-dict>
            </div>
            <div v-if="item.type === 1">
              <single-choice :question="item"></single-choice>
            </div>
            <div v-if="item.type === 2">
              <multiple-choice :question="item"></multiple-choice>
            </div>
            <div v-if="item.type === 3">
              <true-false :question="item"></true-false>
            </div>
            <div v-if="item.type ===4">
              <short-answer :question="item"></short-answer>
            </div>
          </el-card>
        </div>

        <el-button type="info"
                   style="float: right;margin: 20px;"
                   @click="$router.back()">返回上一页</el-button>
        <el-button type="success"
                   style="float: right;margin: 20px;"
                   @click="submitForm">提交</el-button>
      </el-col>

    </el-row>

  </div>
</template>


<script>
import SingleChoice from './type/SingleChoice.vue'
import MultipleChoice from './type/MultipleChoice.vue'
import TrueFalse from './type/TrueFalse.vue'
import ShortAnswer from './type/ShortAnswer.vue'
export default {
  components: { MultipleChoice, SingleChoice, TrueFalse, ShortAnswer },
  data () {
    return {
      questionList: [],
      paperId: null
    }
  },
  mounted () {
    this.paperId = this.$route.query.paperId
    this.getQuestion()
  },
  methods: {
    submitForm () {
      let form = this.questionList.map(item => {
        return {
          id: item.id,
          score: item.score,
          totalScore: item.totalScore,
          userId: item.userId,
          examId: item.examId,
          paperId: item.paperId,
        }
      })
      this.axios.post(`/exam/userpaper/submitScore`, form).then(data => { this.$router.back() }).catch(e => { })
    },
    getQuestion () {
      this.axios.post(`/exam/userpaper/getQuestion?paperId=${this.paperId}`).then(data => {
        this.questionList = [...data.singleChoice, ...data.multipleChoice, ...data.trueFalse, ...data.shortAnswer]
      }).catch(e => { })
    },
  },
}

</script>
<style scoped>
.box {
  background: #e5ecf3;
  min-height: 100vh;
  margin: -20px;
}
</style>