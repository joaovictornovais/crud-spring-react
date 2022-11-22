import React from 'react'
import { Link } from 'react-router-dom';

const Navbar = () => {
    return (
        <>
            <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
                <div className="container-fluid">
                    <Link to={"/"} className="navbar-brand" href="#">CRUD Spring + React</Link>
                    <Link to="/adduser"><button className="btn btn-outline-light">Add User</button></Link>
                </div>
            </nav>
        </>
    )
}

export default Navbar