<template>
  <div id="app">
    <TopBarView/>
    <router-view></router-view>
  </div>
</template>
<script>
import TopBarView from './components/TopBarView.vue';

export default {
  name: 'App',
  components: {
    TopBarView
  }
}
</script>