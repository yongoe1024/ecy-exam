<template>
  <div>
    <el-page-header @back="$router.back()"
                    style="margin-bottom:30px"
                    content="组卷"></el-page-header>
    <el-row>
      <el-col :offset="1"
              :span="3">
        <el-steps :active="active"
                  style="height: 500px;"
                  direction="vertical"
                  finish-status="success">
          <el-step title="考试范围">
          </el-step>
          <el-step title="选择题目"></el-step>
          <el-step title="完成配置"></el-step>
        </el-steps>
      </el-col>
      <el-col :span="16"
              :offset="3">
        <open-type :next="next"
                   v-show="active == 0"></open-type>
      </el-col>
      <el-col :span="16"
              :offset="3">
        <select-bank :next="next"
                     v-show="active == 1"></select-bank>
      </el-col>
      <el-col :span="16"
              :offset="3">
        <finish :next="next"
                v-show="active == 3|| active==2"></finish>
      </el-col>
    </el-row>

  </div>
</template>


<script>
import Finish from './finish.vue'
import OpenType from './open-type.vue'
import SelectBank from './select-bank.vue'
export default {
  components: { OpenType, SelectBank, Finish },
  data () {
    return {
      active: 0,
      buttontext: '下一步'
    }
  },
  methods: {
    next (num) {
      this.active += num
    },
  },
}

</script>
<style scoped>
</style>