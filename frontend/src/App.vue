<template>
    <div id="app">
        <Navbar/>
        <router-view/>
    </div>
</template>

<script>
    import Navbar from './components/common/Navbar.vue'

    export default {
        name: 'App',
        components: {Navbar},
        data: () => {
            return {}
        },
        methods: {},
        created: function () {
        }
    }
</script>

<style>
</style>

