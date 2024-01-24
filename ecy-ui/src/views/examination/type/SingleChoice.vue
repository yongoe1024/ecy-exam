<template>
  <div>
    <div class="ql-editor"
         v-html="question.content"></div>
    <el-divider></el-divider>
    <div v-for="(item, index) in question.questionOption"
         :key="index">
      <el-radio v-model="question.answer"
                class="answer"
                border
                :label="item.id">
        <span>{{`${String.fromCharCode(65+index)}. `}}</span>
        <span class="ql-editor"
              style="display:inline-block;width:90%"
              v-html="item.content"></span>
      </el-radio>
    </div>
  </div>
</template>


<script>
export default {
  props: ["question"],
  data () {
    return {
    }
  },
  mounted () {
  },
  methods: {
  },
}
</script>
<style scoped >
.answer {
  margin-bottom: 10px;
  width: 100%;
  white-space: pre-wrap;
  line-height: 1.5;
}
.el-radio.is-bordered {
  height: auto !important;
  min-height: 65px !important;
}
</style>