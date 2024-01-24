<template>
  <div>
    <div class="ql-editor"
         v-html="question.content"></div>
    <el-divider></el-divider>
    <e-editor v-model="question.answer"></e-editor>
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
<style  >
/* .ql-editor {
  min-height: 65px !important;
} */
</style>