import logo from './logo.svg';
import './App.css';
import Template from './Template';
import { useEffect,useState } from 'react';


function App() {
  const [sources, setSources] = useState([])
  const [selectedSource, setSelectedSource] = useState('')
  const [template, setTemplate] = useState(null)
  const [message, setMessage] = useState('')
  
  
  const onSourceSelect = (e) => {
    const source = e.target.value
    if (source === "" ) {
      setTemplate(null)
      return
    }
    setSelectedSource(source)
    const template = sources.find(src => src.type === source)
    setTemplate(template)
  }


  const postSubmit = (message) => {
    setTemplate(null)
    setSelectedSource('')
    setMessage(message)
    setTimeout(() => {
      setMessage('')
    }, 3000)

  }

  useEffect(() => {
    fetch("http://localhost:8080/rudderstacks/v1/templates")
    .then(res => res.json())
    .then((data) => {
      setSources(data.data)
    })
  }, [])
  
  return (
    <>
    <div className='container'>
    <header><strong>Welcome to rudderstacks!!!!</strong></header>
    <div className='sources-form'>
      <label>
        Select Source:
      </label>
      <select name="source" id="source" value={selectedSource} onChange={(e) => onSourceSelect(e)}>
        <option value="" key="placeholder">Select</option>
        {
          sources.map(src => {
            return <option value={src.type} key={src.type}>
              {src.type}
            </option>
          } 
          )
        }
      </select>
    </div>
    {template && <Template data={template} postSubmit={postSubmit}/>}
    {message && <b>{message}</b>}
    </div>
    </>

  )
  
  

}

export default App;
