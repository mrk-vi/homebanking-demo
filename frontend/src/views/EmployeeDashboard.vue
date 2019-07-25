<template>
    <employee-operations-list></employee-operations-list>
</template>

<script>

    import EmployeeOperationsList from "../components/employee/EmployeeOperationList";
    export default {
        name: "EmployeeDashboard",
        components: {EmployeeOperationsList},
    }
</script>

<style scoped>

</style>