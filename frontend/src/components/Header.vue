<template>
  <nav
    class="text-white container md:w-[80%] mx-auto py-5 flex items-center justify-between"
  >
    <RouterLink to="/">
      <div class="text-4xl font-semibold">
        <span>Schooll</span>
        <span class="text-yellow-500 italic">App</span>
      </div>
    </RouterLink>
    <div>
      <RouterLink to="addStudent" class="hover:text-yellow-500"
        >Add new</RouterLink
      >
    </div>
  </nav>
</template>
<script setup lang="ts"></script>
