<template>
<div>
  <!--头部导航-->
  <Header/>
  <!--主体内容-->
  <div style="display: flex">
    <!--    侧边栏-->
    <Aside style="width: 200px"></Aside>
    <!--        主体-->
    <router-view style="flex: 1"></router-view>
  </div>
</div>
</template>

<script>
import Header from "@/components/Header";
import Aside from "@/components/Aside";

export default {
  name: "layout",
  components: {
    Header,
    Aside,
  }
}
</script>

<style scoped>

</style>