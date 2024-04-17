<template>
  <div style="display:inline-block;">
    <el-button type="primary"
               style="margin-left: 10px;"
               :disabled="bankId==null"
               round
               size="mini"
               icon="el-icon-plus"
               @click="dialogVisible = true">添加题目</el-button>

    <el-dialog :visible.sync="dialogVisible"
               :fullscreen="true"
               :close-on-click-modal="false">

      <el-form>
        <el-row>
          <el-col :offset="2">
            <el-form-item label="选题类型">
              <el-switch style="margin-left:10px"
                         v-model="form.isAutoSelect"
                         active-text="随机选题"></el-switch>
            </el-form-item>
            <el-form-item label="题型">
              <el-row>
                <el-col :offset="1"
                        :span="4">单选 </el-col>
                <el-col :offset="1"
                        :span="4">多选 </el-col>
                <el-col :offset="1"
                        :span="4">判断 </el-col>
                <el-col :offset="1"
                        :span="4">简答 </el-col>
              </el-row>
            </el-form-item>

            <el-form-item label="题数"
                          v-if="form.isAutoSelect">
              <el-row class="num">
                <el-col :offset="1"
                        :span="4">
                  <el-input-number size="small"
                                   :min="0"
                                   :max="bank.singleChoiceNum"
                                   v-model="form.singleChoiceNum"></el-input-number>{{` / ${bank.singleChoiceNum}`}}
                </el-col>
                <el-col :offset="1"
                        :span="4">
                  <el-input-number size="small"
                                   :min="0"
                                   :max="bank.multipleChoiceNum"
                                   v-model="form.multipleChoiceNum"></el-input-number>{{` / ${bank.multipleChoiceNum}`}}
                </el-col>
                <el-col :offset="1"
                        :span="4">
                  <el-input-number size="small"
                                   :min="0"
                                   :max="bank.trueFalseNum"
                                   v-model="form.trueFalseNum"></el-input-number>{{` / ${bank.trueFalseNum}`}}
                </el-col>
                <el-col :offset="1"
                        :span="4">
                  <el-input-number size="small"
                                   :min="0"
                                   :max="bank.shortAnswerNum"
                                   v-model="form.shortAnswerNum"></el-input-number> {{` / ${bank.shortAnswerNum}`}}
                </el-col>
              </el-row>
            </el-form-item>

            <el-form-item label="分值">
              <el-row class="num">
                <el-col :offset="1"
                        :span="4">
                  <el-input-number size="small"
                                   :min="0"
                                   v-model="form.singleChoiceScore"></el-input-number>
                </el-col>
                <el-col :offset="1"
                        :span="4">
                  <el-input-number size="small"
                                   :min="0"
                                   v-model="form.multipleChoiceScore"></el-input-number>
                </el-col>
                <el-col :offset="1"
                        :span="4">
                  <el-input-number size="small"
                                   :min="0"
                                   v-model="form.trueFalseScore"></el-input-number>
                </el-col>
                <el-col :offset="1"
                        :span="4">
                  <el-input-number size="small"
                                   :min="0"
                                   v-model="form.shortAnswerScore"></el-input-number>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
        </el-row>

        <el-divider></el-divider>

        <el-form-item v-if="!form.isAutoSelect">
          <div class="head">
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

          <el-button type="primary"
                     size="mini"
                     plain
                     icon="el-icon-search"
                     @click="getQuestiontList">搜索</el-button>
          <el-button size="mini"
                     plain
                     icon="el-icon-refresh"
                     @click="resetQuery">重置</el-button>

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
                             width="100"
                             align="center">
              <template slot-scope="scope">
                <e-dict name="题目类型"
                        v-model="scope.row.type"
                        type="tag"></e-dict>
              </template>
            </el-table-column>
            <el-table-column prop="level"
                             label="题目难度"
                             width="100"
                             align="center"></el-table-column>
          </el-table>
          <el-pagination background
                         style="display:flex;justify-content:center;"
                         @size-change="handleSizeChange"
                         @current-change="handleCurrentChange"
                         :page-size="size"
                         layout="total, sizes, prev, pager, next, jumper"
                         :total="total"></el-pagination>
        </el-form-item>
      </el-form>

      <span slot="footer">
        <el-button plain
                   type="success"
                   icon="el-icon-plus"
                   @click="handleAddQuestion">添加题目</el-button>
        <el-button @click="dialogVisible = false">关闭</el-button>
      </span>
    </el-dialog>
  </div>
</template>


<script>
export default {
  props: {
    bankId: {
      default: () => null
    },
    value: {}
  },
  watch: {
    bankId: {
      immediate: true,
      handler (nv) {
        this.form.totalScore = 0
        this.getBankInfo()
        this.getQuestiontList()
      }
    },
    value: {
      immediate: true,
      handler (nv) {
        this.myValue = nv
      },
    },
    myValue: {
      immediate: true,
      handler (nv) {
        this.$emit("input", nv)
      }
    },
  },
  data () {
    return {
      form: {
        bankId: null,
        questionIdList: [],
        isAutoSelect: false,
        totalScore: 0,

        singleChoiceNum: 0,
        multipleChoiceNum: 0,
        trueFalseNum: 0,
        shortAnswerNum: 0,

        singleChoiceScore: 0,
        multipleChoiceScore: 0,
        trueFalseScore: 0,
        shortAnswerScore: 0,
      },
      bank: {
        singleChoiceNum: 0,
        multipleChoiceNum: 0,
        trueFalseNum: 0,
        shortAnswerNum: 0,
      },
      queryParam: {
        content: null,
        type: null,
        level: null,
      },
      multipleSelection: [],
      total: 0,
      current: 1,
      size: 10,
      dataList: [],
      dialogVisible: false,
      myValue: [],
    }
  },
  mounted () {
    this.getBankInfo()
    this.getQuestiontList()
  },
  methods: {
    handleAddQuestion () {
      if (this.form.isAutoSelect) {
        let a = this.form.singleChoiceNum * this.form.singleChoiceScore
        let b = this.form.multipleChoiceNum * this.form.multipleChoiceScore
        let c = this.form.trueFalseNum * this.form.trueFalseScore
        let d = this.form.shortAnswerNum * this.form.shortAnswerScore
        this.form.totalScore = a + b + c + d
        this.form.bankId = this.bankId
        this.myValue = this.form
        this.$message.success('随机抽题')
        return
      }
      this.form.questionIdList = this.multipleSelection.map(item => item.id)
      let total = 0
      this.multipleSelection.forEach(item => {
        if (item.type == '1')
          total += this.form.singleChoiceScore
        else if (item.type == '2')
          total += this.form.multipleChoiceScore
        else if (item.type == '3')
          total += this.form.trueFalseScore
        else if (item.type == '4')
          total += this.form.shortAnswerScore
      })
      this.form.totalScore = total
      this.myValue = this.form
      this.$message.success('添加了' + this.multipleSelection.length + '道题目')
    },
    resetQuery () {
      this.queryParam = this.$options.data().queryParam
    },
    // 多选框回调
    handleSelectionChange (val) {
      this.multipleSelection = val
    },
    // 改变页码
    handleSizeChange (val) {
      this.size = val
      this.getQuestiontList()
    },
    // 点击页数
    handleCurrentChange (val) {
      this.current = val
      this.getQuestiontList()
    },
    // 获取题目列表
    getQuestiontList () {
      this.queryParam.questionBankId = this.bankId
      this.axios.post(`/exam/question/page?current=${this.current}&size=${this.size}`, this.queryParam).then(data => {
        this.dataList = data.list
        this.total = data.total - 0
      }).catch(e => { })
    },
    getBankInfo () {
      this.axios.post('/exam/config/bankInfo?bankId=' + this.bankId).then(data => {
        this.bank = data
      }).catch(e => { })
    },
  }
}

</script>
<style scoped>
.num .el-input {
  width: 70px !important;
}
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
.content {
  max-height: 100px;
}
.content >>> img {
  display: none;
}
</style>