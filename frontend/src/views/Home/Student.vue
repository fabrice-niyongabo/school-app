<template>
  <tr>
    <td class="text-xs p-2">{{ props.student.id }}</td>
    <td class="text-xs p-2">{{ props.student.firstname }}</td>
    <td class="text-xs p-2">{{ props.student.lastname }}</td>
    <td class="text-xs p-2">{{ props.student.email }}</td>
    <td class="text-xs p-2">
      <v-btn size="small" color="red" :loading="isLoading" @click="handleDelete"
        >Delete</v-btn
      >
    </td>
  </tr>
</template>
<script lang="ts" setup>
import { PropType, ref } from "vue";
import { IStudent } from "./Home.vue";
import { errorHandler, toastMessage } from "../../utils";
import axios from "axios";
import { BACKEND_URL } from "../../constants";

const emit = defineEmits(["fetch-students"]);

const props = defineProps({
  student: {
    type: Object as PropType<IStudent>,
    required: true,
  },
});

const isLoading = ref(false);
const handleDelete = async () => {
  if (
    confirm(`Do you want to delete student with email: ${props.student.email}?`)
  ) {
    try {
      isLoading.value = true;
      const res = await axios.delete(
        BACKEND_URL + "/students/" + props.student.id
      );
      toastMessage("success", res.data.message);
      emit("fetch-students");
    } catch (error) {
      errorHandler(error);
    } finally {
      isLoading.value = false;
    }
  }
};
</script>
