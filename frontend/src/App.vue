<template>
  <div className="bg-gray-950 min-h-screen">
    <Header />
    <main>
      <RouterView />
    </main>
  </div>
</template>

<script setup lang="ts">
import Header from "./components/Header.vue";
</script>
