<template>
  <div class="vue-tempalte-outer">
    <!-- Navigation -->
    <Navigation />

    <!-- Main -->
    <div class="App">
      <div class="vertical-center">
        <div class="inner-block">
          <router-view />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import Navigation from './components/Navbar';

  export default {
      components: {
          'Navigation': Navigation
      },
      data() {
        return {

        };
      }
  }
</script>