<script setup>
import Menu from '../components/MenuCom.vue'

</script>

<template>
  <!-- 菜单栏 -->
  <Menu/>
  <!-- 内容 -->
  <RouterView/>
</template>

<style scoped>
#logo {
  width: 37px;
  height: 37px;
  margin-top: 6px;
}

#flex-grow1 {
  flex-grow: 1;
}

#flex-grow2 {
  flex-grow: 0;
}

/* .el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
} */

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.gray-color {
  background-color: rgb(178, 171, 171);
}
</style>../components/MenuCom.vue