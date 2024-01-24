<template>
  <div>
    <el-form :model="form"
             ref="form"
             label-width="100px"
             style="margin:20px">
      <el-form-item v-for="(item, index) in form.bankList"
                    :label="'题库' + (index+1)"
                    :key="index"
                    :prop="'bankList[' + index + ']'"
                    :rules="{ required: true, message: '不能为空', trigger: 'blur'  }">
        <el-select v-model="form.bankList[index]"
                   filterable
                   placeholder="请选择题库">
          <el-option v-for="bank in dataList"
                     :key="bank.id"
                     :label="bank.name"
                     :value="bank.id">
          </el-option>
        </el-select>
        <select-question v-if="form.bankList[index]!=null"
                         :bank-id="form.bankList[index]"
                         v-model="form.questionList[index]"></select-question>
        <el-button v-if="index > 0"
                   type="danger"
                   round
                   size="mini"
                   style="margin-left: 10px;"
                   @click.prevent="removeBank(index)">删除</el-button>

      </el-form-item>

      <el-form-item>
        <el-button round
                   size="small"
                   type="success"
                   icon="el-icon-plus"
                   @click="addBank">新增题库</el-button>
      </el-form-item>

      <el-form-item label="总分">
        <el-input-number readonly
                         v-model="form.totalScore"></el-input-number>
      </el-form-item>
      <el-form-item label="及格分"
                    prop="passScore">
        <el-input-number v-model="form.passScore"
                         :min="0"
                         :max="form.totalScore"></el-input-number> <el-button type="text"
                   @click="countScore">计算</el-button>
      </el-form-item>
      <el-form-item>
        <el-button style="margin-top:30px"
                   @click="submitForm">下一步</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>


<script>
import SelectQuestion from './select-question.vue'
export default {
  components: { SelectQuestion },
  props: ['next'],
  computed: {},
  watch: {},
  data () {
    return {
      dataList: [],
      form: {
        bankList: [null],
        questionList: [{}],
        totalScore: 0,
        passScore: 0,
      },
    }
  },
  mounted () {
    this.getbankList()
  },
  methods: {
    countScore () {
      let totalScore = 0
      for (let i = 0; i < this.form.questionList.length; i++) {
        let questionList = this.form.questionList[i]
        totalScore += questionList.totalScore
      }
      this.form.totalScore = totalScore
      this.form.passScore = Math.floor(this.form.totalScore * 0.6)
    },
    submitForm () {
      this.$refs.form.validate(valid => {
        if (valid) {
          let bankList = this.form.bankList
          let bankListSet = new Set(bankList)
          if (bankList.length != bankListSet.size) {
            this.$message.error('题库不能重复')
            return
          }
          for (let i = 0; i < this.form.questionList.length; i++) {
            let question = this.form.questionList[i]
            if (typeof (question.isAutoSelect) != "undefined") {
              if (question.isAutoSelect) {
                if (0 == question.singleChoiceNum + question.multipleChoiceNum + question.trueFalseNum + question.shortAnswerNum) {
                  this.$message.error('题库 [' + (i + 1) + '] 没有题目')
                  return
                }
              } else {
                if (question.questionIdList.length == 0) {
                  this.$message.error('题库 [' + (i + 1) + '] 没有题目')
                  return
                }
              }
            } else {
              this.$message.error('题库 [' + (i + 1) + '] 没有题目')
              return
            }
          }
          window.localStorage.setItem('form2', JSON.stringify(this.form))
          this.next(1)
        }
      })
    },
    //删除题库
    removeBank (item) {
      this.form.bankList.splice(item, 1)
      this.form.questionList.splice(item, 1)
    },
    //添加题库
    addBank () {
      this.form.bankList.push(null)
      this.form.questionList.push([])
    },
    // 初始化数据
    getbankList () {
      this.axios.post(`/exam/config/bank`).then(data => {
        this.dataList = data
      }).catch(e => { })
    },
  },
}

</script>
<style scoped>
</style>