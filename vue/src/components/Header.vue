<template>
  <div  style="background: pink; height: 50px; line-height: 50px; border-bottom: 1px; display: flex ; ">
    <div style="width: 200px; color: teal;font-size: large;padding: 2px">后台管理</div>
    <div style="flex: 1"></div>
    <div style="width: 100px">
      <el-dropdown>
         <span class="el-dropdown-link">
           黄龙<i class="el-icon-arrow-down el-icon--right"></i>
      </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>个人信息</el-dropdown-item>
            <el-dropdown-item divided @click="$router.push('/login')" >退出</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>

  </div>
</template>

<script>

</script>

<style scoped>

</style>