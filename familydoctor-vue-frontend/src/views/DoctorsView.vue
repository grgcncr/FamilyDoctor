<script setup>
import { onMounted, ref } from 'vue';
import { useRemoteData } from '@/services/useRemoteData.js';

const urlRef = ref('http://localhost:9090/api/doctor');
const authRef = ref(true);
const { data, loading, performRequest } = useRemoteData(urlRef, authRef);

onMounted(() => {
  performRequest();
});
</script>

<template>
  <div class="bg-body-tertiary">
    <div class="container">
      <div class="row py-4 px-3">
        <div class="col-12">
          <div class="mb-4">
            <h1 class="fs-3">Doctors</h1>
          </div>
          <div>
            <table class="table">
              <thead>
              <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Phone Number</th>
                <th>Actions</th>
              </tr>
              </thead>
              <tbody v-if="loading">
              <tr>
                <td colspan="5">Loading...</td>
              </tr>
              </tbody>
              <tbody v-if="data">
              <tr v-for="doctor in data._embedded.doctors">
                <td>{{ doctor.id }}</td>
                <td>{{ doctor.firstName }}</td>
                <td>{{ doctor.lastName }}</td>
                <td>{{ doctor.phoneNumber }}</td>
                <td>
                  <RouterLink
                      :to="{
                                                name: 'doctor-details',
                                                params: { id: doctor.id }
                                            }"
                  >Display</RouterLink
                  >
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>