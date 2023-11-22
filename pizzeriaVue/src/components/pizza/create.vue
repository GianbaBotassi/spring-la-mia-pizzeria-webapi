<template>
  <h2>Crea una nuova pizza</h2>
  <form @submit.prevent="createPizza">
    <div class="form-group">
      <label for="name">Nome</label>
      <input
        type="text"
        class="form-control"
        id="name"
        v-model="formPizza.name"
      />
    </div>
    <div class="form-group">
      <label for="description">Descrizione</label>
      <input
        type="text"
        class="form-control"
        id="description"
        v-model="formPizza.description"
      />
    </div>
    <div class="form-group">
      <label for="picture">Foto</label>
      <input
        type="text"
        class="form-control"
        id="picture"
        v-model="formPizza.picture"
      />
    </div>
    <div class="form-group">
      <label for="price">Prezzo</label>
      <input
        type="number"
        class="form-control"
        id="price"
        v-model="formPizza.price"
        step="0.01"
      />
    </div>
    <button class="mt-3 rounded" type="submit">Aggiungi pizza</button>
  </form>
  <router-link :to="{ name: 'List' }" class="mt-2 btn btn-primary">
    Torna alla lista
  </router-link>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      formPizza: {
        name: "",
        description: "",
        picture: "",
        price: "",
      },
    };
  },
  methods: {
    createPizza(formPizza) {
      axios
        .post(`http://localhost:8080/api/pizzas`, this.formPizza)
        .then((response) => {
          console.log(response);
          this.$router.push({ name: "List" });
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
<style lang="">
</style>