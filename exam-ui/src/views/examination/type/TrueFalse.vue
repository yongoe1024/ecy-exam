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
        <span class="ql-editor"
              style="display:inline-block;"
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
  font-size: 30px !important;
  margin-bottom: 10px;
  width: 100%;
}
.el-radio.is-bordered {
  height: auto !important;
  min-height: 65px !important;
}
</style>