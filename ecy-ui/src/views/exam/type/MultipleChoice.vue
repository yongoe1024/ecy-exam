<template>
  <div>
    <el-row>
      <el-col :span="24"
              class="col">
        <div class="ql-editor"
             v-html="question.content"></div>
      </el-col>
    </el-row>
    <el-checkbox-group v-model="value"
                       style="pointer-events: none;">
      <el-checkbox v-for="(item, index) in question.questionOption"
                   :key="index"
                   class="answer"
                   :class="getClass(item)"
                   border
                   :label="item.id">
        <span>{{`${String.fromCharCode(65+index)}. `}}</span>
        <span class="ql-editor"
              style="display:inline-block;"
              v-html="item.content"></span>
      </el-checkbox>
    </el-checkbox-group>
    <div style="float:right;margin:20px;">
      <span>得分：</span>
      <el-input-number v-if="!disabled"
                       :min="0"
                       :max="question.totalScore"
                       v-model="question.score"></el-input-number>
      <span v-if="disabled">
        <el-link v-if="question.isTrue"
                 style="font-size:30px"
                 type="success">{{question.score}}</el-link>
        <el-link v-else
                 style="font-size:30px"
                 type="danger">{{question.score}}</el-link>
      </span>
    </div>
  </div>
</template>
<script>
export default {
  props: ["question", "disabled"],
  data () {
    return {
      value: []
    }
  },
  mounted () {
    //根据逗号分隔答案
    this.value = this.question.answer.split(',')
    if (this.question.isTrue == null) {
      let flag = true
      for (let i = 0; i < this.question.questionOption.length; i++) {
        let item = this.question.questionOption[i]
        //答案错误，已勾选
        if (!item.isTrue && this.value.includes(item.id)) {
          flag = false
        }
        // 答案正确未勾选
        if (item.isTrue && !this.value.includes(item.id)) {
          flag = false
        }
      }
      if (flag) {
        this.question.score = this.question.totalScore
      }
    }
  },
  methods: {
    getClass (item) {
      if (item.isTrue) {
        return 'green'
      }
      for (let i = 0; i < this.value.length; i++) {
        if (this.value[i] == item.id) {
          return 'red'
        }
      }
    },
  },
}
</script>
<style  scoped>
.col {
  padding: 10px;
  box-shadow: 0 0 3px #aca6a6;
}
.answer {
  margin-bottom: 10px;
  width: 100%;
  white-space: pre-wrap;
  line-height: 1.5;
}
.el-checkbox.is-bordered {
  margin-left: 0px !important;
  min-height: 65px !important;
  height: auto !important;
}
.ql-editor >>> img {
  max-width: 50%;
}
.green {
  background-color: rgb(130, 229, 130);
}
.red {
  background-color: rgb(235, 98, 98);
}
</style>