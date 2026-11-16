import { useState } from "react";
import { useEffect } from "react";

function Spordialad() {
const [spordialad, setSpordialad] = useState([]);

useEffect(() => {
  fetch("http://localhost:9000/spordialad")
  .then(res => res.json())
  .then(data => setSpordialad(data))
}, [])

return (
    <>
    <div>Spordialade nimekiri; {spordialad.length}</div>
    <div>
        <table>
            
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Spordiala</th>
                </tr>
            </thead>
            <tbody>
            {spordialad.map(spordiala =>
                <tr key={spordiala.id}>
                    <td>{spordiala.id}</td>
                    <td>{spordiala.name}</td>
            </tr>)}
            </tbody>
        </table>
    </div>
    </>
)
}

export default Spordialad