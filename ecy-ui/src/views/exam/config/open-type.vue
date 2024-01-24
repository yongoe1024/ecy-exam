<template>
  <div>
    <el-form ref="form"
             :model="form"
             label-width="auto"
             style="margin:20px"
             :rules="rules">
      <el-form-item prop="openType">
        <e-dict name="考试范围"
                type="radio"
                v-model="form.openType"> </e-dict>
      </el-form-item>
      <el-form-item v-if="form.openType == 2">
        <select-dept v-model="form.departmentIdList"> </select-dept>
      </el-form-item>
      <el-form-item v-if="form.openType == 3">
        <select-user v-model="form.userIdList"></select-user>
      </el-form-item>
      <el-form-item>
        <el-button style="margin-top:30px"
                   @click="submitForm">下一步</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>


<script>
import SelectDept from './select-dept.vue'
import SelectUser from './select-user.vue'
export default {
  components: { SelectDept, SelectUser },
  props: ['next'],
  data () {
    return {
      form: {
        openType: null,
        departmentIdList: [],
        userIdList: [],
      },
      rules: { openType: [{ required: true, message: '请选择开放类型', trigger: 'change' }] },
    }
  },
  mounted () { },
  methods: {
    submitForm () {
      this.$refs.form.validate(valid => {
        if (valid) {
          if (this.form.openType == '2' && this.form.departmentIdList.length == 0) {
            this.$message.error('请选择部门')
            return
          }
          if (this.form.openType == '3' && this.form.userIdList.length == 0) {
            this.$message.error('请选择用户')
            return
          }
          window.localStorage.setItem('form', JSON.stringify(this.form))
          this.next(1)
        }
      })
    },
  },
}

</script>
<style scoped>
</style>