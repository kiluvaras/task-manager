<template>
    <div>
        <b-btn>Add task</b-btn>
        <p>
            <input type="text" placeholder="Enter your task here" v-model="task.name">
        </p>
        <p>
            <textarea placeholder="Description for the task" v-model="task.desc"></textarea>
        </p>
        <p>
            <button type="submit" class="btn btn-primary" @click="add">Add task</button>
        </p>
        <p>{{ task.name }}</p>
        <p>{{ task.desc }}</p>
    </div>
</template>

<script>
    // @ is an alias to /src
    // import HelloWorld from '@/components/HelloWorld.vue'
    import axios from "axios";

    export default {
        name: 'addTask',
        data() {
            return {
                task: {
                    name: '',
                    desc: ''
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