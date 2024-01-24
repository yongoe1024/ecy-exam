<template>
  <div>
    <el-row>
      <el-col :span="24"
              class="col">
        <div class="ql-editor"
             v-html="question.content"></div>
      </el-col>
      <!-- <el-col :offset="1"
              class="col"
              :span="11">
        标准答案<div class="ql-editor"
             v-html="question.answer"></div>
      </el-col> -->
    </el-row>
    <el-divider></el-divider>
    <!-- 学生答案 -->
    <div class="ql-editor col"
         style="width: 90%;"
         v-html="question.answer"></div>
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
  mounted () { },
  methods: {},
}

</script>
<style  scoped>
.col {
  padding: 10px;
  box-shadow: 0 0 3px #aca6a6;
}
.ql-editor >>> img {
  max-width: 50%;
}
</style>