import './App.css'
import { Link, Route, Routes } from 'react-router-dom'
import Avaleht from './pages/Avaleht';
import Sportlased from './pages/Sportlased';
import Spordialad from './pages/Spordialad';
import Tulemused from './pages/Tulemused';

function App() {

  /// PAGES kuvamine
return (
  <> 
  <Link to="/"><button>Avaleht</button></Link>
  <Link to="/sportlased"><button>Sportlased</button></Link>
  <Link to="/spordialad"><button>Spordialad</button></Link>
  <Link to="/tulemused"><button>Tulemused</button></Link>

  <hr></hr>

  <Routes>
    <Route path="/" element={< Avaleht />} />
    <Route path="/sportlased" element={< Sportlased />} />
    <Route path="/spordialad" element={< Spordialad />} />
    <Route path="/tulemused" element={< Tulemused />} />
    
  </Routes>

</>
)
}
     export default App


