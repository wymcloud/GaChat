<template>
  <div id="app">
    <router-view/>
     <el-container>
        <el-header>Header</el-header>
        <el-main>Main</el-main>
        <el-footer>Footer</el-footer>
      </el-container>
  </div>
  
</template>

<script>
export default {
  name: 'App'
}
</script>


