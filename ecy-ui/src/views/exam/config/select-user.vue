<template>
  <div>

    <el-card style="width:60%;margin:5px">
      <div>
        <span>已选择用户</span>
      </div>
      <el-tag v-for="(item,index) in nameList"
              style="margin:5px"
              :key="index">{{item}}</el-tag>
    </el-card>
    <el-button type="primary"
               style="margin-left: 10px;"
               round
               size="mini"
               icon="el-icon-plus"
               @click="dialogVisible = true">选择用户</el-button>
    <el-dialog :visible.sync="dialogVisible"
               width="70%"
               :close-on-click-modal="false">
      <!-- 搜索 -->
      <div class="head">
        <el-input size="small"
                  prefix-icon="el-icon-search"
                  placeholder="请输入姓名"
                  v-model="queryParam.name"></el-input>
        <el-input size="small"
                  prefix-icon="el-icon-search"
                  placeholder="请输入邮箱"
                  v-model="queryParam.email"></el-input>
        <el-input size="small"
                  prefix-icon="el-icon-search"
                  placeholder="请输入联系电话"
                  v-model="queryParam.phone"></el-input>
        <el-select v-model="queryParam.enabled"
                   size="small"
                   placeholder="是否启用">
          <el-option label="是"
                     :value="true"></el-option>
          <el-option label="否"
                     :value="false"></el-option>
        </el-select>
      </div>
      <!-- 按钮 -->
      <div class="button">
        <el-button type="primary"
                   size="mini"
                   plain
                   icon="el-icon-search"
                   @click="getList">搜索</el-button>
        <el-button size="mini"
                   plain
                   icon="el-icon-refresh"
                   @click="resetQuery">重置</el-button>

      </div>
      <el-table :data="dataList"
                :header-cell-style="{background:'#eef1f6'}"
                @selection-change="handleSelectionChange"
                style="width: 100%">
        <el-table-column align="center"
                         type="selection"
                         width="55"></el-table-column>
        <el-table-column align="center"
                         type="index"></el-table-column>
        <el-table-column prop="username"
                         label="账号"
                         align="center"></el-table-column>
        <el-table-column prop="name"
                         label="姓名"
                         align="center"></el-table-column>
        <el-table-column prop="departmentName"
                         label="部门"
                         align="center"></el-table-column>
        <el-table-column prop="positionName"
                         label="职位"
                         align="center"></el-table-column>
        <el-table-column prop="avatar"
                         label="头像"
                         align="center">
          <template slot-scope="scope">
            <img :src="scope.row.avatar"
                 style="width:40px">
          </template>
        </el-table-column>
        <el-table-column prop="enabled"
                         label="是否启用"
                         align="center">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.enabled"
                    effect="dark"
                    type="success">启用
            </el-tag>
            <el-tag v-else
                    effect="dark"
                    type="danger">禁用
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="roleList"
                         label="角色"
                         align="center">
          <template slot-scope="scope">
            <el-tag v-for="item in scope.row.roleList"
                    style="margin:2px"
                    size="small"
                    :key="item.id">{{item.name}}</el-tag>
          </template>
        </el-table-column>
      </el-table>
      <span slot="footer">
        <el-button type="success"
                   icon="el-icon-plus"
                   @click="handleAdd">添加</el-button>
        <el-button @click="dialogVisible = false">关闭</el-button>
      </span>
    </el-dialog>
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
      dialogVisible: false,
      // 多选框数据
      multipleSelection: [],
      nameList: [],
      total: 0,
      current: 1,
      size: 10,
      myValue: [],

      myValue: [],
      dataList: [],
      queryParam: {

      },
    }
  },
  mounted () { this.getList() },
  methods: {
    handleAdd () {
      this.myValue = this.multipleSelection
      this.$message.success('添加了' + this.multipleSelection.length + '人')
    },
    resetQuery () {
      this.queryParam = this.$options.data().queryParam
    },
    // 多选框回调
    handleSelectionChange (val) {
      this.multipleSelection = val.map(item => item.id)
      this.nameList = val.map(item => item.name)
    },
    // 改变页码
    handleSizeChange (val) {
      this.size = val
      this.getQuestiontList()
    },
    // 点击页数
    handleCurrentChange (val) {
      this.current = val
      this.getQuestiontList()
    },
    getList () {
      this.axios.post(`/exam/config/user?current=${this.current}&size=${this.size}`, this.queryParam).then(data => {
        this.dataList = data.list
        this.total = data.total - 0
      }).catch(e => { })
    },
  },
}

</script>
<style scoped>
.num .el-input {
  width: 70px !important;
}
.head {
  display: flex;
  flex-wrap: wrap;
}
.head .el-input {
  width: 200px !important;
}
.head * {
  margin: 0 8px 8px 0;
}
</style>