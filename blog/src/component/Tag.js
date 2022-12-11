import { useQuery } from 'react-query'
import { Link } from "react-router-dom";
import Loading from './Loading'
import Error from './Error'
export default function Post() {
    const { isLoading, error, data: category } = useQuery('category', () =>
    fetch('http://localhost:8080/category').then(res => res.json()));

    if (isLoading) return <Loading/>;
    if (error) return <Error message={error.message}/>;
    return(
        <div class="container flex flex-row p-8 justify-center items-center">
            <div class="items-center justify-center px-4">
        {category.map(tag=>(
            <Link to="/" state={{tag}}>
                <button type="button" key={tag} class="basis-1/4 py-2.5 px-5 mr-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-full border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700">#{tag}</button>
            </Link>
        ))}
        </div>
        </div>
    );
}