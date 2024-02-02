<!--<template>-->
<!--  <div class="snippet-container">-->
<!--    <h1 class=""> Doctor List </h1>-->
<!--    <table class="">-->
<!--      <thead>-->
<!--      <th>Id</th>-->
<!--      <th>FirstName</th>-->
<!--      <th>LastName</th>-->
<!--      <th>PhoneNumber</th>-->
<!--      </thead>-->
<!--      <tbody>-->
<!--      <tr v-for="doctor in doctors" :key="doctor.id">-->
<!--        <td>{{ doctor.id }}</td>-->
<!--        <td>{{ doctor.firstName }}</td>-->
<!--        <td>{{ doctor.lastName }}</td>-->
<!--        <td>{{ doctor.phoneNumber }}</td>-->
<!--      </tr>-->
<!--      </tbody>-->
<!--    </table>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import DoctorService from "@/services/DoctorService";-->

<!--export default {-->
<!--  name: 'DoctorList',-->
<!--  data() {-->
<!--    return {-->
<!--      doctors: [],-->
<!--    };-->
<!--  },-->
<!--  created: function () {-->
<!--    console.log('Fetching doctors...');-->
<!--    DoctorService.getDoctors()-->
<!--        .then(doctors => {-->
<!--          // Update the component's data with the fetched doctors-->
<!--          this.doctors = doctors;-->
<!--        })-->
<!--        .catch(error => {-->
<!--          console.error('Error fetching doctors:', error);-->
<!--        });-->
<!--  },-->
<!--};-->
<!--</script>-->

<!--<style>-->
<!--/* Add your styles here if needed */-->
<!--</style>-->
