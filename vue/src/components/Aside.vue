<template>
  <div >
    <el-menu
        style="min-height: calc(100vh - 50px)"
        :default-active="path"
        class="el-menu-vertical-demo"
        router>
      <el-menu-item index="home" >用户管理</el-menu-item>
      <el-menu-item index="blog" >博客管理</el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Aside",
  data(){
    return{
      path: this.$route.path
    }
  }

}
</script>

<style scoped>

</style>