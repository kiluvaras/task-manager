<template>
    <div>
        <b-table
                striped
                hover
                responsive
                :items="tasks"
                :fields="fields"
        >
            <template slot="actions" slot-scope="row">
                <b-button class="btn-success m-2" @click="finishTask(row.item)">
                    DONE
                </b-button>
                <b-button class="btn-primary m-2" @click="editTask(row.item, row.index, $event.target)">
                    EDIT
                </b-button>
                <b-button class="btn-danger m-2" @click="deleteTask(row.item)">
                    DELETE
                </b-button>
            </template>
        </b-table>

        <b-modal id="modalInfo" @hide="resetModal" hide-footer>
            <div class="form-group">
                <input type="text" v-model="modalInfo.description" class="form-control"
                       placeholder="">
            </div>
            <b-button class="mt-3" variant="primary" block @click="send">Save</b-button>
        </b-modal>

    </div>
</template>

<script>
    // @ is an alias to /src
    import axios from "axios";

    export default {
        name: 'taskTable',
        data() {
            return {
                tasks: [],
                fields: ['id', 'description', 'completed', 'actions'],
                modalInfo: {
                    id: '',
                    description: '',
                    completed: ''
                },
            }
        },
        methods: {
            deleteTask(item) {
                axios.delete('http://localhost:8080/task/' + item.id)
                    .then(response => (this.success = response.data.success, this.updateTable()));
            }
            ,
            updateTable() {
                axios.get('http://localhost:8080/task').then(response => (this.tasks = response.data));
            },
            editTask(item, index, button) {
                this.modalInfo.description = item.description;
                this.modalInfo.id = item.id;
                this.modalInfo.completed = item.completed;
                this.modalInfo.content = JSON.stringify(item);
                this.$root.$emit('bv::show::modal', 'modalInfo', button)
            },
            resetModal() {
                this.modalInfo.id = '';
                this.modalInfo.description = '';
                this.modalInfo.completed = '';

            },
            send() {
                axios.post('http://localhost:8080/edit',
                    this.modalInfo
                ).then(response => (this.success = response.data,
                    this.updateTable()));
            },
            finishTask(item) {
                axios.post('http://localhost:8080/task/complete/' + item.id
                ).then(response => (this.success = response.data,
                    this.updateTable()));
            }
        },
        created() {
            axios.get('http://localhost:8080/task').then(response => (this.tasks = response.data));
        }
    }
</script>