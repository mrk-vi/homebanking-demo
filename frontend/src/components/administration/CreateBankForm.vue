<template>
    <form action="">
        <div class="modal-card" style="width: 680px">
            <header class="modal-card-head">
                <p class="modal-card-title">Create Bank</p>
            </header>
            <section class="modal-card-body">
                <b-field label="Bank Name">
                    <b-input
                            type="text"
                            v-model="form.bankName"
                            placeholder="Goldman Sachs"
                            required
                    >
                    </b-input>
                </b-field>
                <b-field label="Bank Description">
                    <b-input
                            type="textarea"
                            v-model="form.bankDescription"
                            placeholder="The Goldman Sachs Group, Inc. is a leading global investment banking, securities and investment management firm that provides a wide range of financial ..."
                    >
                    </b-input>
                </b-field>
                <b-field label="Bank Address">
                    <b-input
                            type="text"
                            v-model="form.bankAddress"
                            placeholder="57 Wall st, New York, NY, USA"
                    >
                    </b-input>
                </b-field>
                <b-field label="Email">
                    <b-input
                            type="email"
                            v-model="form.mail"
                            placeholder="Manager email"
                            required>
                    </b-input>
                </b-field>

                <b-field label="Username">
                    <b-input
                            type="text"
                            v-model="form.username"
                            placeholder="Manager username"
                            required>
                    </b-input>
                </b-field>

                <template>
                    <section>
                        <b-field label="Images">
                            <b-upload v-model="dropFiles"
                                      multiple
                                      drag-drop>
                                <section class="section">
                                    <div class="content has-text-centered">
                                        <p>
                                            <b-icon
                                                    icon="upload"
                                                    size="is-large">
                                            </b-icon>
                                        </p>
                                        <p>Drop your files here or click to upload</p>
                                    </div>
                                </section>
                            </b-upload>
                        </b-field>

                        <div class="tags">
                            <span v-for="(file, index) in dropFiles"
                                  :key="index"
                                  class="tag is-primary" >
                                {{file.name}}
                                <button class="delete is-small"
                                        type="button"
                                        @click="deleteDropFile(index)">
                                </button>
                            </span>
                        </div>
                    </section>
                </template>
            </section>
            <footer class="modal-card-foot">
                <button class="button" type="button" @click="$parent.close()">Close</button>
                <button class="button is-primary" type="submit" @click="submit">Submit</button>
            </footer>
        </div>
    </form>
</template>

<script>
    import {mapGetters} from 'vuex'
    import BField from "buefy/src/components/field/Field";
    import BInput from "buefy/src/components/input/Input";

    export default {
        name: "CreateBankForm",
        data: () => {
            return {
                form: {
                    bankName: '',
                    bankDescription: '',
                    bankAddress: '',
                    mail: '',
                    username: ''
                },
                dropFiles: []
            }
        },
        components: {BInput, BField},
        computed: {
            ...mapGetters(['client'])
        },
        methods: {
            submit: async function (evt) {
                const loadingComponent = this.$loading.open({container: null})
                evt.preventDefault()
                const res = await this.client.apis['admin-dashboard'].createBankUsingPOST({form: this.form})
                const bank = JSON.parse(res.data)
                for (let i = 0; i<this.dropFiles.length; i++){
                    await this.client.apis['images-controller'].uploadBankImageUsingPOST({file: this.dropFiles[i], id: bank.id})
                }
                loadingComponent.close()
                this.$parent.close()
            },
            deleteDropFile(index) {
                this.dropFiles.splice(index, 1)
            }
        }
    }
</script>

<style scoped>

</style>