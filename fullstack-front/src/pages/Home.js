import React, { useEffect, useState } from 'react'
import axios from 'axios';
import { BsPencilFill, BsEyeFill, BsTrashFill } from 'react-icons/bs'
import { Link, useParams } from 'react-router-dom';

const Home = () => {

    const [users, setUsers] = useState([]);

    const { id } = useParams();

    useEffect(() => {
        loadUsers();
    })

    const loadUsers = async () => {
        const result = await axios.get("http://localhost:8080/api/users");
        setUsers(result.data);
    }

    const deleteUser = async (id) => {
        await axios.delete(`http://localhost:8080/api/users/${id}`);
        loadUsers();
    }

    return (
        <div className="container">
            <div className="py-4">
                <table className="table border shadow">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Name</th>
                            <th scope="col">Username</th>
                            <th scope="col">E-mail</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>

                        {
                            users.map((user, index) => (
                                <tr>
                                    <th scope="row" key={index}>{index + 1}</th>
                                    <td>{user.name}</td>
                                    <td>{user.username}</td>
                                    <td>{user.email}</td>
                                    <td>
                                        <Link to={`edituser/${user.id}`}><button className="btn btn-primary mx-2"><BsPencilFill /></button></Link>
                                        <Link to={`viewuser/${user.id}`}><button className="btn btn-outline-primary mx-2"><BsEyeFill /></button></Link>
                                        <button className="btn btn-danger mx-2" onClick={() => deleteUser(user.id)}><BsTrashFill /></button>
                                    </td>
                                </tr>
                            ))
                        }


                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default Home