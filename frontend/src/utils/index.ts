import { AxiosError } from "axios";
import { useToast } from "vue-toast-notification";

type TToastType = "success" | "error" | "info";

export const toastMessage = (type: TToastType, message: string) => {
  const toast = useToast();
  if (type === "success") {
    toast.success(message, { position: "top-right" });
  }
  if (type === "error") {
    toast.error(message, { position: "top-right" });
  }

  if (type === "info") {
    toast.info(message, { position: "top-right" });
  }
};

export const errorHandler = (error: unknown) => {
  if (error instanceof AxiosError) {
    if (error.response) {
      toastMessage("error", error.response.data?.message || error.message);
    } else {
      toastMessage("error", error.message);
    }
  } else if (error instanceof Error) {
    toastMessage("error", error.message);
  } else {
    toastMessage("error", "Error: something went wrong");
  }
};
