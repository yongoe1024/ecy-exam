<template>
  <div class="box">
    <el-row style="padding:10px;">
      <el-col :md="5"
              class="col">
        <el-row>
          <el-card class="left"
                   v-if="singleChoice.length!=0">
            <div slot="header">
              <span>单选</span>
            </div>
            <el-tag v-for="(i,index) in singleChoice"
                    :key="index"
                    effect="dark"
                    :type="i.answer?'success':'info'"
                    class="tag"
                    :class="present.id==i.id?'tagcolor':''"
                    @click="clickTag(index)">{{index+1}}</el-tag>
          </el-card>
        </el-row>

        <el-row>
          <el-card class="left"
                   v-if="multipleChoice.length!=0">
            <div slot="header">
              <span>多选</span>
            </div>
            <el-tag v-for="(i,index) in multipleChoice"
                    :key="index"
                    effect="dark"
                    :type="i.answer?'success':'info'"
                    class="tag"
                    :class="present.id==i.id?'tagcolor':''"
                    @click="clickTag(multipleChoiceNum+index)">{{multipleChoiceNum+index+1}}</el-tag>
          </el-card>
        </el-row>

        <el-row>
          <el-card class="left"
                   v-if="trueFalse.length!=0">
            <div slot="header">
              <span>判断</span>
            </div>
            <el-tag v-for="(i,index) in trueFalse"
                    :key="index"
                    effect="dark"
                    :type="i.answer?'success':'info'"
                    class="tag"
                    :class="present.id==i.id?'tagcolor':''"
                    @click="clickTag(trueFalseNum+index)">{{trueFalseNum+index+1}}</el-tag>
          </el-card>
        </el-row>

        <el-row>
          <el-card class="left"
                   v-if="shortAnswer.length!=0">
            <div slot="header">
              <span>简答</span>
            </div>
            <el-tag effect="dark"
                    :type="i.answer?'success':'info'"
                    class="tag"
                    :class="present.id==i.id?'tagcolor':''"
                    v-for="(i,index) in shortAnswer"
                    :key="index"
                    @click="clickTag(shortAnswerNum+index)">{{shortAnswerNum+index+1}}</el-tag>
          </el-card>
        </el-row>
      </el-col>

      <el-col :sm="24"
              :md="13"
              class="col">
        <el-card>
          <div slot="header">
            <span>题目分值：{{present.totalScore}}</span>
          </div>
          <div :style="!deadlineEnable?{ 'pointer-events': 'none' }:{}">
            <single-choice v-if="present.type==1"
                           :question="present"></single-choice>
            <multiple-choice v-else-if="present.type==2"
                             :question="present"> </multiple-choice>
            <true-false v-else-if="present.type==3"
                        :question="present"> </true-false>
            <short-answer v-else-if="present.type==4"
                          :question="present"> </short-answer>
          </div>

          <div style="float:right;margin:10px;">
            <el-button type="primary"
                       :disabled="questionList.indexOf(present)==0"
                       round
                       size="medium"
                       @click="last">上一题</el-button>
            <el-button type="primary"
                       :disabled="questionList.indexOf(present)==questionList.length-1"
                       round
                       size="medium"
                       @click="next">下一题</el-button>
          </div>
        </el-card>

      </el-col>
      <el-col :sm="24"
              :md="5"
              class="col">
        <el-card>
          <h3>{{exam.examName}}</h3>
          <div style="font-size: 14px;">试卷总分: {{exam.totalScore}}</div>
          <div style="font-size: 14px;">及格分: {{exam.passScore}}</div>
          <div style="font-size: 14px;">考试时间: {{exam.duration}}分钟</div>
          <el-divider></el-divider>
          <div style="width: 100%; display: inline-block; "
               v-if="deadlineEnable">
            <el-statistic :value="deadline"
                          time-indices
                          title="剩余时间"
                          @finish="finish"
                          format="HH:mm:ss">
            </el-statistic>
          </div>
          <el-divider></el-divider>
          <div v-if="deadlineEnable"
               style=" display: flex;justify-content: center;">
            <el-button type="primary"
                       round
                       size="medium"
                       @click="submit">交卷</el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>


<script>
import 'element-ui/lib/theme-chalk/display.css'
import "./disable.js"
import SingleChoice from './type/SingleChoice.vue'
import MultipleChoice from './type/MultipleChoice.vue'
import TrueFalse from './type/TrueFalse.vue'
import ShortAnswer from './type/ShortAnswer.vue'
export default {
  components: { MultipleChoice, SingleChoice, TrueFalse, ShortAnswer },
  computed: {},
  watch: {
    present: {
      handler (nv, oldv) {
        if (oldv && oldv.answer) {
          if (this.deadlineEnable) {
            //切换前，如果当前有答案，就提交
            let form = {
              answer: oldv.answer,
              id: oldv.id,
              paperId: this.paper.id
            }
            this.axios.post('/exam/userpaper/saveAnswer', form).then(data => {
            }).catch(e => { })
          }
        }
      },
    },
  },
  data () {
    return {
      deadline: Date.now() + 1000 * 60 * 60 * 8,
      deadlineEnable: false,
      exam: {},
      paper: {},

      // 当前题目
      present: {},

      questionList: [],
      singleChoice: [],
      multipleChoice: [],
      trueFalse: [],
      shortAnswer: [],

      singleChoiceNum: 0,
      multipleChoiceNum: 0,
      trueFalseNum: 0,
      shortAnswerNum: 0,
    }
  },
  mounted () {
    this.getExam()
  },
  methods: {
    submit () {
      this.$confirm('是否交卷?', '提示', { type: 'warning' }).then(() => {
        this.handPaper()
      }).catch(e => { })
    },
    handPaper () {
      //提交最后一道题
      let form = {
        answer: this.present.answer,
        id: this.present.id,
        paperId: this.paper.id
      }
      this.axios.post('/exam/userpaper/saveAnswer', form).then(data => {
      }).catch(e => { })
      this.axios.post(`/exam/userpaper/handPaper?examId=${this.exam.id}`).then(data => {
        this.$confirm('考试结束，是否退出?', '提示', { type: 'warning' }).then(() => {
          window.close()
        }).catch(e => { })
        this.getExam()
      }).catch(e => { })
    },

    clickTag (index) {
      this.present = this.questionList[index]
    },
    last () {
      if (this.questionList.indexOf(this.present) - 1 >= 0)
        this.present = this.questionList[this.questionList.indexOf(this.present) - 1]
    },
    next () {
      if (this.questionList.indexOf(this.present) + 1 <= this.questionList.length - 1)
        this.present = this.questionList[this.questionList.indexOf(this.present) + 1]
    },
    finish () {
      this.$message({
        message: '考试时间到',
        type: 'warning'
      })
    },
    getQuestion (paperId) {
      this.axios.post(`/exam/userpaper/getQuestion?paperId=${paperId}`).then(data => {
        this.singleChoice = data.singleChoice
        this.multipleChoice = data.multipleChoice
        this.trueFalse = data.trueFalse
        this.shortAnswer = data.shortAnswer
        this.singleChoiceNum = 0
        this.multipleChoiceNum = this.singleChoice.length
        this.trueFalseNum = this.singleChoice.length + this.multipleChoice.length
        this.shortAnswerNum = this.singleChoice.length + this.multipleChoice.length + this.trueFalse.length
        this.questionList = [...data.singleChoice, ...data.multipleChoice, ...data.trueFalse, ...data.shortAnswer]
        this.present = this.questionList[0]
      }).catch(e => { })
    },

    getExam () {
      let examId = this.$route.query.examId
      this.axios.post(`/exam/userpaper/exam?examId=${examId}`).then(data => {
        this.exam = data.exam
        this.paper = data.paper
        this.getQuestion(this.paper.id)
        if (this.paper.status == 3 || this.paper.status == 4 || this.paper.status == 5) {
          this.$message({
            message: '考试已结束',
            type: 'warning'
          })
          this.deadlineEnable = false
        } else
          this.configTime()
      }).catch(e => { })
    },

    configTime () {
      let start = this.exam.startTime
      let end = this.exam.endTime
      let enter = this.paper.enterTime
      let duration = this.exam.duration * 1000 * 60
      //计算倒计时
      let result
      let startTime = new Date(start)
      let endTime = new Date(end)
      let enterTime = new Date(enter)
      if (enterTime.getTime() + duration <= endTime.getTime())
        result = enterTime.getTime() + duration
      else
        result = endTime.getTime()
      this.deadline = result
      this.deadlineEnable = true
    },
  },
}

</script>
<style scoped>
.box {
  min-height: 100vh;
  background: #eff3f7;
}
.col {
  margin-right: 10px;
  margin-bottom: 5px;
}
.left {
  width: 100%;
  margin-bottom: 10px;
}
.tag {
  margin: 5px;
  width: 35px;
  cursor: pointer;
}
.tagcolor {
  background-color: #409eff;
  border-color: #409eff;
}
</style>