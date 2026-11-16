import { useState } from "react";
import { useEffect } from "react";




function Tulemused() {
 const [tulemused, setTulemused] = useState([]); /// Muutuja tektitamine ja pannakse products HTLMIS , setter muudab products setProducts(korraga uuendab ka HMTLIS )
  const [sportlased, setSportlased] = useState([]);

useEffect(() => {
  fetch("http://localhost:9000/tulemused")
  .then(res => res.json())
  .then(data => setTulemused(data))
}, [])

useEffect(() => {  
  fetch("http://localhost:9000/sportlased")
.then(res => res.json())
.then(data => setSportlased(data));

}, []);


   return (
  <> 


<div>Tulemused: {tulemused.length}</div>
<div>
    <table className="tulemuste-tabel">
        <thead>
            <tr>
                <th>ID</th>
                <th>Sportlane</th>
                <th>Spordiala</th>
                <th>Tulemus</th>
                <th>Punktid</th>
                
            </tr>
        </thead>
    <tbody>
  {sportlased.map(sportlane => ( 
    <tr key={sportlane.id}>
      <td>{sportlane.id}</td>
      <td>{sportlane.name}</td>
      
      
      
      </tr>))}
      </tbody>
      </table>
</div>
</>
) 
}
export default Tulemused
