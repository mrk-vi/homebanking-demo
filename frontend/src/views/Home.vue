<template>
    <div id="home">
        <div class="hero is-primary is-bold">
            <div class="hero-body">
                <div class="container">
                    <p class="title">
                        Your bank at home
                    </p>
                </div>
            </div>
        </div>
        <BanksList/>
    </div>
</template>

<script>
    import BanksList from '../components/visitor/BanksList.vue'

    export default {
        name: 'home',
        components: {
            BanksList
        }
    }
</script>
