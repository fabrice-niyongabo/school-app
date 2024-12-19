<template>
  <div
    class="container md:w-[80%] mx-auto pt-5 pb-10 bg-gray-900 p-5 rounded-md text-white"
  >
    <div class="w-full overflow-auto">
      <table class="w-full">
        <thead>
          <tr>
            <th class="text-sm font-semibold text-left p-2">#</th>
            <th class="text-sm font-semibold text-left p-2">Firstname</th>
            <th class="text-sm font-semibold text-left p-2">Lastname</th>
            <th class="text-sm font-semibold text-left p-2">Email</th>
            <th class="text-sm font-semibold text-left p-2">Action</th>
          </tr>
        </thead>
        <tbody>
          <Student
            v-for="student of students"
            :key="student.id"
            :student="student"
            @fetch-students="fetchStudents"
          />
        </tbody>
      </table>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, ref } from "vue";
import { errorHandler } from "../../utils";
import axios from "axios";
import { BACKEND_URL } from "../../constants";
import Student from "./Student.vue";

export interface IStudent {
  id: number;
  firstname: string;
  lastname: string;
  email: string;
}

const isLoading = ref(false);
const students = ref<IStudent[]>([]);

const fetchStudents = async () => {
  try {
    isLoading.value = true;
    const res = await axios.get(BACKEND_URL + "/students");
    students.value = res.data;
  } catch (error) {
    errorHandler(error);
  } finally {
    isLoading.value = false;
  }
};

onMounted(() => {
  fetchStudents();
});
</script>
