<template>
  <div>
    <el-tree ref="tree"
             :check-strictly="true"
             :data="dataList"
             :default-expand-all="true"
             show-checkbox
             node-key="id"
             :props="{children: 'children', label: 'name', keyname: 'id' }"></el-tree>
    <el-button round
               size="small"
               type="success"
               icon="el-icon-plus"
               @click="handlAdd()">添加部门</el-button>
  </div>
</template>


<script>
export default {
  props: {
    value: {
      type: Array,
      default: () => []
    },
  },
  watch: {
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
      myValue: [],
      dataList: [],
    }
  },
  mounted () { this.getList() },
  methods: {
    handlAdd () {
      let tree = this.$refs.tree
      //  父节点与子节点 ，传入的时候无关联，1. 是否只是叶子节点，默认值为 false 2. 是否包含半选节点，默认值为 false
      let selectedKeys = tree.getCheckedNodes(false, true)
      this.myValue = selectedKeys.map(item => item.id)
      this.$message.success('添加了' + selectedKeys.length + '个部门')
    },
    getList () {
      this.axios.post('/exam/config/department').then(data => {
        this.dataList = data
      }).catch(e => { })
    }
  },
}
</script>
<style scoped>
</style>