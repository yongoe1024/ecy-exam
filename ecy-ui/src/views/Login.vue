<template>
  <div class="root">

    <el-form v-loading="loading"
             :rules="rules"
             ref="form"
             :model="form"
             class="form">
      <h1 class="titleText">{{$TITLE}}</h1>

      <el-form-item prop="username">
        <el-input v-model="form.username"
                  placeholder="请输入用户名或邮箱"></el-input>
      </el-form-item>

      <el-form-item prop="password">
        <el-input show-password
                  v-model="form.password"
                  @keyup.enter.native="submitForm"
                  placeholder="请输入密码"></el-input>
      </el-form-item>
      <!-- <el-row>
          <el-col :span="14">
            <el-form-item prop="code">
              <el-input type="text"
                        @keydown.enter.native="submitForm"
                        v-model="form.code"
                        placeholder="点击图片更换验证码"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9"
                  :offset="1">
            <img @click="updateCaptcha"
                 :src="captchaUrl" />
          </el-col>
        </el-row> -->

      <el-form-item>
        <el-button style="width: 100%"
                   type="primary"
                   @click="submitForm">登录</el-button>
      </el-form-item>
      <div class="end">
        <router-link :to="{path: 'register'}">注册账号</router-link>
        <router-link :to="{path: 'forget'}">忘记密码？</router-link>
      </div>
    </el-form>

  </div>
</template>

<script>
export default {
  data () {
    return {
      loading: false,
      captchaUrl: this.$BASE_URL + '/captcha',
      form: {
        username: '',
        password: '',
        code: '',
      },
      rules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'change' }],
        password: [{ required: true, message: '请输入密码', trigger: 'change' }],
        code: [{ required: true, message: '请输入验证码', trigger: 'change' }],
      },
    }
  },
  methods: {
    updateCaptcha () {
      this.captchaUrl = this.$BASE_URL + '/captcha' + '?' + new Date()
    },
    submitForm () {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.loading = true
          this.axios.post('/login', this.form).then((token) => {
            // 存入 session
            window.localStorage.setItem('token', token)
            // 取出重定向的网页
            let path = this.$route.query.redirect
            this.$router.replace(
              path == '/login' || path == undefined ? '/index' : path
            )
            this.loading = false
          }).catch(() => {
            this.updateCaptcha()
            this.form.code = ''
            this.loading = false
          })
        }
      })
    },
  },
}
</script>

<style scoped>
.root {
  height: 100%;
  width: 100%;
  position: absolute;
  background-image: url("../assets/back.jpg");
  background-size: 100% 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.form {
  width: 350px;
  padding: 40px;
  background-color: rgba(203, 206, 208, 0.9);
  border-radius: 10px;
}
.titleText {
  font-size: 20px;
  font-family: 楷体;
}
.end {
  display: flex;
  margin: 20px auto;
  justify-content: space-between;
}
.end a {
  font-weight: 500;
  text-decoration: none;
  font-size: 14px;
  color: rgb(110, 119, 120);
}
</style>