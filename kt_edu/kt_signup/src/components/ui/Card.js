export default function Card({ children, className = '', variant = 'default' }) {
  const baseClasses = 'bg-white overflow-hidden shadow-lg rounded-lg'
  
  const variants = {
    default: '',
    hover: 'hover:shadow-xl transition-shadow duration-300',
    gradient: 'bg-gradient-to-r from-blue-50 to-indigo-50 border border-blue-200'
  }
  
  const classes = `${baseClasses} ${variants[variant]} ${className}`
  
  return (
    <div className={classes}>
      {children}
    </div>
  )
} 