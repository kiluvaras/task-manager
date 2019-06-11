<template>
    <div>
        <p>
            <input type="text" placeholder="Enter your task here" v-model="task.description">
        </p>
        <p>
            <button type="submit" class="btn btn-primary" @click="add">Add task</button>
        </p>
    </div>
</template>

<script>
    // @ is an alias to /src
    import axios from "axios";

    export default {
        name: 'addTask',
        data() {
            return {
                task: {
                    description: ''
                }
            }
        },
        methods: {
            add() {
                axios.post('http://localhost:8080/task', this.task).then(response => (this.success = response.data));
            }
        }
    }
</script>