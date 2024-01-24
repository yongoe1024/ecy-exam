<template>
  <div>
    <el-row>
      <el-col :span="24"
              class="col">
        <div class="ql-editor"
             v-html="question.content"></div>
      </el-col>
    </el-row>

    <div v-for="(item, index) in question.questionOption"
         style="pointer-events: none;"
         :key="index">
      <el-radio v-model="question.answer"
                class="answer"
                border
                :class="getClass(item)"
                :label="item.id">
        <span>{{`${String.fromCharCode(65+index)}. `}}</span>
        <span class="ql-editor"
              style="display:inline-block;"
              v-html="item.content"></span>
      </el-radio>
    </div>

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
    }
  },
  mounted () {
    if (this.question.isTrue == null) {
      for (let i = 0; i < this.question.questionOption.length; i++) {
        let item = this.question.questionOption[i]
        if (item.isTrue && this.question.answer == item.id) {
          this.question.score = this.question.totalScore
        }
      }
    }
  },
  methods: {
    getClass (item) {
      if (item.isTrue) {
        return 'green'
      }
      if (this.question.answer == item.id) {
        return 'red'
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
.el-radio.is-bordered {
  height: auto !important;
  min-height: 45px !important;
}
.green {
  background-color: rgb(130, 229, 130);
}
.red {
  background-color: rgb(235, 98, 98);
}
</style>