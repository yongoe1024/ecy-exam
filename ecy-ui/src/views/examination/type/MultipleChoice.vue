<template>
  <div>
    <div class="ql-editor"
         v-html="question.content"></div>
    <el-divider></el-divider>
    <el-checkbox-group v-model="value">
      <el-checkbox v-for="(item, index) in  question.questionOption"
                   :key="index"
                   class="answer"
                   border
                   :label="item.id">
        <span>{{`${String.fromCharCode(65+index)}. `}}</span>
        <span class="ql-editor"
              style="display:inline-block;"
              v-html="item.content"></span>
      </el-checkbox>
    </el-checkbox-group>
  </div>
</template>


<script>
export default {
  props: ["question"],
  watch: {
    'question.answer': {
      immediate: true,
      handler (nv, ov) {
        if (nv != ov) {
          this.value = nv.split(",")
        }
      },
    },
    value: {
      handler (nv, ov) {
        this.question.answer = nv.join(",")
      },
    },
  },
  data () {
    return {
      value: []
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
.el-checkbox.is-bordered {
  margin-left: 0px !important;
  min-height: 65px !important;
  height: auto !important;
}
</style>