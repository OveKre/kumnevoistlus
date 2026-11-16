import { useState } from 'react'  // Hoiab andmeid komponendi olekus.
import { useEffect } from 'react' // Käivitab API päringu lehe avamisel.

function Sportlased() {
  // Backendist saadud sportlaste nimekiri; alguses tühi massiiv.
  const [sportlased, setSportlased] = useState([]);

  // Näitab, kas esialgne sportlaste päring veel kestab.
  const [laeb, setLaeb] = useState(true);

  // Hoiab päringu veateadet, kui päring ebaõnnestub.
  const [viga, setViga] = useState(null);

  // Vormiväljade väärtused.
  const [nimi, setNimi] = useState('');
  const [vanus, setVanus] = useState('');
  const [riik, setRiik] = useState('');

  // null = lisame uut sportlast; ID = muudame olemasolevat sportlast.
  const [muudetavId, setMuudetavId] = useState(null);

  // Küsib lehe avamisel backendist olemasolevad sportlased.
  useEffect(() => {
    fetch("http://localhost:9000/sportlased")
      .then(res => res.json()) // Teisendab serveri JSON-vastuse JavaScripti andmeteks.
      .then(data => {
        setSportlased(data); // Paneb saadud nimekirja olekusse ja uuendab vaadet.
      })
      .catch(() => setViga('Sportlaste laadimine ebaõnnestus'))
      .finally(() => setLaeb(false)); // Lõpetab laadimise ka päringu vea korral.
  }, []); // Tühi sõltuvuste massiiv: päring tehakse lehe avamisel.

  // Saadab vormi andmed backendile uue sportlase loomiseks.
  function lisaSportlane(event) {
    event.preventDefault(); // Takistab brauseril vormi saatmisel lehte uuesti laadida.

    fetch("http://localhost:9000/sportlased", {
      method: "POST", // POST loob uue sportlase.
      headers: { "Content-Type": "application/json" }, // Saadame JSON-andmed.
      body: JSON.stringify({
        name: nimi, // Vormis on „nimi“, kuid backend ootab välja „name“.
        vanus,
        riik,
      }),
    })
      .then(res => res.json())
      .then(uusSportlane => {
        // Lisab serveri tagastatud sportlase koos tema ID-ga tabelisse.
        setSportlased(eelmised => [...eelmised, uusSportlane]);

        // Tühjendab vormiväljad pärast lisamist.
        setNimi('');
        setVanus('');
        setRiik('');
      });
  }

  // Saadab valitud sportlase muudetud andmed backendile.
  function muudaSportlane(event) {
    event.preventDefault();

    fetch("http://localhost:9000/sportlased", {
      method: "PUT", // PUT muudab olemasoleva sportlase andmeid.
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        id: muudetavId, // ID ütleb backendile, millist sportlast muuta.
        name: nimi,
        vanus,
        riik,
      }),
    })
      .then(res => res.json())
      .then(uuendatudSportlane => {
        // Asendab tabelis ainult selle sportlase, kelle ID vastab uuendatud ID-le.
        setSportlased(eelmised =>
          eelmised.map(sportlane =>
            sportlane.id === uuendatudSportlane.id
              ? uuendatudSportlane
              : sportlane
          )
        );

        // Tühjendab vormi ja viib selle tagasi lisamise režiimi.
        setNimi('');
        setVanus('');
        setRiik('');
        setMuudetavId(null);
      });
  }

  // „Muuda“ nupu vajutamisel täidab vormi valitud sportlase andmetega.
  function valiMuutmiseks(sportlane) {
    setMuudetavId(sportlane.id);
    setNimi(sportlane.name);
    setVanus(sportlane.vanus);
    setRiik(sportlane.riik);
  }

  function kustutaSportlane(id) {
    fetch(`http://localhost:9000/sportlased/${id}`, {  // Kui fetch sees on URL koos muutujaga {id} - siis tuleb kasut. BACKSTICKke . see javascripti nõue
        method: "DELETE",
    
    })
    .then(res => res.json())
    .then(uusNimekiri => setSportlased(uusNimekiri));
  }

  // Enne päringu lõppu näitame laadimise teksti.
  if (laeb) return <div>Laeb</div>;

  // Päringu vea korral näitame veateadet.
  if (viga) return <div>{viga}</div>;

  return (
    <>
      {/* Sportlaste arv tuleb sportlased-massiivi pikkusest. */}
      <div>Sportlaste nimekiri: {sportlased.length}</div>

      <div>
        <h2>Lisa Sportlane</h2>

        {/* ID puudumisel lisab vorm sportlase; ID olemasolul muudab teda. */}
        <form onSubmit={muudetavId === null ? lisaSportlane : muudaSportlane}>
          {/* htmlFor seob sildi sisestusväljaga, mille id on „nimi“. */}
          <label htmlFor="nimi">Nimi</label>
          <input
            id="nimi"
            type="text"
            value={nimi} // Väljal kuvatakse nimi-oleku väärtus.
            onChange={event => setNimi(event.target.value)} // Salvestab sisestatu olekusse.
          />

          <label htmlFor="vanus">Vanus</label>
          <input
            id="vanus"
            type="text"
            value={vanus}
            onChange={event => setVanus(event.target.value)}
          />

          <label htmlFor="riik">Riik</label>  {/* Loob tühjad lahtrid kuhu siestatakse mingi andmetüüp! */}
          <input
            id="riik"
            type="text"
            value={riik}
            onChange={event => setRiik(event.target.value)}
          />

          {/* Nupu tekst näitab, kas vorm lisab või muudab sportlast. */}
          <button type="submit">
            {muudetavId === null ? 'Lisa sportlane' : 'Salvesta muudatus'}
          </button>
        </form>

        <table>
          {/* Päised annavad veergude nimed. Nende järjekord vastab allolevatele td-lahtritele. */}
          <thead>
            <tr>
              <th>ID</th>
              <th>Nimi</th>
              <th>Vanus</th>
              <th>Riik</th>
              <th>Tegevus</th>
            </tr>
          </thead>

          {/* tbody sisaldab sportlaste andmeridu. */}
          <tbody>
            {/* map loob iga sportlase jaoks ühe rea; key aitab Reactil ridu eristada. */}
            {sportlased.map(sportlane => (
              <tr key={sportlane.id}>
                <td>{sportlane.id}</td>
                <td>{sportlane.name}</td>
                <td>{sportlane.vanus}</td>
                <td>{sportlane.riik}</td>
                <td>
                  {/* See nupp valib sportlase muutmiseks; vormi see ei saada. */}
                  <button
                    type="button"
                    onClick={() => valiMuutmiseks(sportlane)}
                  >
                    Muuda
                  </button>
                  <button type="button" onClick={() => kustutaSportlane(sportlane.id)}>
                    Kustuta
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
}

export default Sportlased;