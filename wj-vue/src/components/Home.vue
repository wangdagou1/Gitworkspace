<template>
    <div>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true" :model="filters">
                <el-form-item>
                    <el-input v-model="filters.name" placeholder="姓名"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" v-on:click="getUsers">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="info" @click="addUser">新增</el-button>
                </el-form-item>
            </el-form>
        </el-col>


        <el-table :data="userInfoList" style="width: 100%">
            <el-table-column prop="id" label="ID" width="180">
            </el-table-column>
            <el-table-column prop="userName" label="名字" width="180">
            </el-table-column>
            <el-table-column prop="userPwd" label="密码" width="180">
            </el-table-column>
            <!--第二步  开始进行修改和查询操作-->
            <el-table-column label="操作" align="center" min-width="100">

                <template slot-scope="scope">

                    <el-button type="text" @click="checkDetail(scope.row)">查看详情</el-button>

                    <el-button type="info" @click="modifyUser(scope.row)">修改</el-button>

                    <el-button type="info" @click="deleteUser(scope.row)">删除</el-button>
                </template>
            </el-table-column>
            <!--编辑与增加的页面-->


        </el-table>
        <!--新增界面-->
        <el-dialog title="记录" :visible.sync="dialogVisible" width="50%" :close-on-click-modal="false">
            <el-form :model="addFormData" :rules="rules2" ref="addFormData" label-width="0px" class="demo-ruleForm login-container">
                <el-form-item prop="userName">
                    <el-input type="text" v-model="addFormData.userName" auto-complete="off" placeholder="账号"></el-input>
                </el-form-item>
                <el-form-item prop="userPwd">
                    <el-input type="password" v-model="addFormData.userPwd" auto-complete="off" placeholder="密码"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click.native="dialogVisible = false,addFormData={id:'',userName:'',userPwd:''}">取消</el-button>
                <el-button v-if="isView" type="primary" @click.native="addSubmit">确定</el-button>
            </span>
        </el-dialog>
    </div>

</template>

<script>
    export default {
        name: 'home',
        data() {
            return {
                userInfoList: [],
                addFormReadOnly: true,
                dialogVisible: false,
                isView: true,
                addFormData: {
                    id: '',
                    userName: '',
                    userPwd: ''
                },
                rules2: {
                    userName: [{
                        required: true,
                        message: '用户名不能为空',
                        trigger: 'blur'
                    }],
                    userPwd: [{
                        required: true,
                        message: '密码不能为空',
                        trigger: 'blur'
                    }]
                },
                filters: {
                    name: ''
                }
            }
        },
        mounted: function () {
            this.loadData();
        },

        methods: {
            loadData() {

              let param = {filter:this.filters.name};
              this.$axios
                .post('/list', {
                })
                .then(successResponse => {
                  console.log(successResponse);
                  var _data = successResponse.data.data.list;
                  this.userInfoList = _data;
                })
                .catch(failResponse => {
                })
            },
            getUsers() {
                this.loadData();
            },
            addUser() {

            },
            checkDetail(rowData) {

            },
            modifyUser(rowData) {

            },
            deleteUser(rowData) {


            },
            addSubmit() {


            }

        }

    }
</script>

<style>
    body {
        background: #DFE9FB;
    }
</style>
