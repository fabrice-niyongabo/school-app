<template>
  <div class="container md:w-[80%] mx-auto pt-5 pb-10 rounded-md text-white">
    <form @submit.prevent="handleSubmit">
      <h1>Register new student</h1>
      <div class="grid grid-cols-2 gap-5 mt-5">
        <input
          type="text"
          required
          v-model="form.firstname"
          :disabled="isLoading"
          placeholder="Enter firstname"
          class="border-gray-300 text-gray-200 border bg-gray-900 px-3 py-2 rounded-md text-sm focus:border-yellow-500 focus:outline-none transition duration-300 disabled:opacity-5"
        />
        <input
          type="text"
          required
          v-model="form.lastname"
          :disabled="isLoading"
          placeholder="Enter lastname"
          class="border-gray-300 text-gray-200 border bg-gray-900 px-3 py-2 rounded-md text-sm focus:border-yellow-500 focus:outline-none transition duration-300 disabled:opacity-5"
        />
        <input
          type="email"
          required
          v-model="form.email"
          :disabled="isLoading"
          placeholder="Enter email"
          class="border border-gray-300 text-gray-200 bg-gray-900 px-3 py-2 rounded-md text-sm focus:border-yellow-500 focus:outline-none transition duration-300 disabled:opacity-5"
        />
      </div>
      <div class="mt-5">
        <v-btn
          type="submit"
          :disabled="isLoading"
          :loading="isLoading"
          class="!bg-yellow-500 !normal-case"
          >Register</v-btn
        >
      </div>
    </form>
  </div>
</template>

<script setup lang="ts">
import axios from "axios";
import { reactive, ref } from "vue";
import { BACKEND_URL } from "../constants";
import { errorHandler, toastMessage } from "../utils";
interface IForm {
  firstname: string;
  lastname: string;
  email: string;
}

const isLoading = ref(false);
const form = reactive<IForm>({
  firstname: "",
  lastname: "",
  email: "",
});

const handleSubmit = async () => {
  try {
    isLoading.value = true;
    await axios.post(BACKEND_URL + "/students", form);
    toastMessage("success", "Student registered!");
    form.firstname = "";
    form.lastname = "";
    form.email = "";
  } catch (error) {
    errorHandler(error);
  } finally {
    isLoading.value = false;
  }
};
</script>
