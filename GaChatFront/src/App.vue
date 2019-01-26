<template>
  <div id="app" class="login-centert">
          <header></header>
          <login></login>
  </div>
</template>

<script>
import header from '@/components/head/head'
import login from '@/components/login/login'

export default {
  components :{header,login},
  name: 'App'
}
</script>

<style>
.el-row {
    margin-bottom: 20px;
    
  }
	.login-box {
		margin-top:20%;
		margin-left:40%;
	}
  .login-centert{
    text-align: center;
  }
</style>


