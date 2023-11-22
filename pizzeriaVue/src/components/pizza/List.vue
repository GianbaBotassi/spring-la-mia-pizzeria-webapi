<template>
  <div class="d-flex justify-content-center align-items-center gap-2">
    <h2>Elenco pizze</h2>
    <router-link :to="{ name: 'create' }">
      <i class="fa-solid fa-circle-plus"></i>
    </router-link>
    <form @submit.prevent="searchPizza">
      <label for="search"></label>
      <input type="text" id="search" v-model="search" />
      <button class="rounded" type="submit">Cerca</button>
    </form>
  </div>
  <table class="table">
    <thead>
      <tr>
        <th scope="col">Nome pizza</th>
        <th scope="col">Descrizione</th>
        <th scope="col">Prezzo</th>
        <th scope="col">Azioni</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(pizza, idx) in listPizza" :key="idx">
        <td>{{ pizza.name }}</td>
        <td>{{ pizza.description }}</td>
        <td>{{ pizza.price }}</td>
        <td>
          <button @click="deletePizza(pizza.id)">
            <i class="fa-solid fa-trash"></i>
          </button>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      listPizza: [],
      search: "",
    };
  },
  methods: {
    deletePizza(pizza) {
      axios
        .delete(`http://localhost:8080/api/pizzas/${pizza}`)
        .then((response) => {
          console.log(response);
          this.getList();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getList(param) {
      axios
        .get(`http://localhost:8080/api/pizzas`)
        .then((response) => {
          this.listPizza = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    searchPizza() {},
  },
  mounted() {
    axios
      .get(`http://localhost:8080/api/pizzas`)
      .then((response) => {
        this.listPizza = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>